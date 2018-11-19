package com.yanshang.jizhui.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.yanshang.jizhui.bean.Group;
import com.yanshang.jizhui.bean.User;
import com.yanshang.jizhui.service.GroupService;
import com.yanshang.jizhui.service.UserService;
import com.yanshang.jizhui.util.ResultString;
import com.yanshang.jizhui.util.SmsClientSend;
import com.yanshang.jizhui.util.TimeTools;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.common.model.RegisterInfo;
import cn.jmessage.api.common.model.message.MessageBody;
import cn.jmessage.api.group.CreateGroupResult;
import cn.jmessage.api.message.SendMessageResult;
import cn.jmessage.api.user.UserClient;
import cn.jmessage.api.user.UserInfoResult;
import redis.clients.jedis.Jedis;

@RestController
public class UserController {

    private static String appkey = "72ea33c57d792065597bfef0";
    private static String masterSecret = "1cd07f744ff937afd8d949b1";
    public static String url = "http://www.qf106.com/sms.aspx?action=send";
    public static String userid = "16150";
    public static String account = "aijizhu";
    public static String password = "123456";
    public static String checkWord = "这个字符串中是否包含了屏蔽字";
    public static final String prefix = "您的验证码是：";
    public static final String suffix = ". 如非本人操作请忽略本条信息";

    JMessageClient client = new JMessageClient(appkey, masterSecret);
    private UserClient userclient = new UserClient(appkey, masterSecret);
    @Resource
    private UserService userService;
    @Resource
    private GroupService groupService;

    @RequestMapping("/register")
    @ResponseBody
    public ResultString<Void> add(String username, String password) {
        ResultString<Void> rr = new ResultString<>();
        User user = new User();
        if (userService.finduserbyusername(username) == null) {


            user.setPassword(password);
            user.setUsername(username);
            userService.save(user);
            List<RegisterInfo> users = new ArrayList<RegisterInfo>();
            RegisterInfo user1 = RegisterInfo.newBuilder().setUsername(username).setPassword(password).build();


            try {//注册admin
                ResponseWrapper res =
                        userclient.registerAdmins(user1);
                users.add(user1);
                rr.setCode(0);
                rr.setMessage("注册成功");
                System.out.println(res);
            } catch (APIConnectionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (APIRequestException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            rr.setCode(1);
            rr.setMessage("用户名已存在!");
        }

        return rr;

    }

    /**
     * 账号密码登陆
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResultString<Void> login(String username, String password) {
        ResultString<Void> rr = new ResultString<>();
        User user = userService.finduserbyusername(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                rr.setCode(0);
                rr.setMessage("登陆成功!!");
            } else {
                rr.setCode(1);
                rr.setMessage("密码错误!");

            }


        } else {
            rr.setCode(1);
            rr.setMessage("用户名不存在!!");


        }


        return rr;


    }

    /**
     * 删除用户
     */
    @RequestMapping("/del")
    @ResponseBody
    public ResultString<Void> del(String username) {
        ResultString<Void> rr = new ResultString<>();

        try {
            client.deleteUser(username);
            User user = userService.finduserbyusername(username);
            if (user == null) {
                rr.setCode(1);
                rr.setMessage("用户名不存在");

            } else {
                userService.del(user.getId());
                client.deleteUser(user.getUsername());
                rr.setCode(0);
                rr.setMessage("删除用户成功!");
            }
        } catch (APIConnectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (APIRequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rr;


    }

    /**
     * 发送单聊消息
     */
    @RequestMapping("/sendmessage")
    @ResponseBody
    public ResultString<Void> sendtxtmessage(String text, String username, String targetname) {
        ResultString<Void> rr = new ResultString<>();
        MessageBody body = MessageBody.text(text);
        try {
            SendMessageResult result = client.sendSingleTextByAdmin(targetname, username, body);
            System.out.println(result);
            rr.setCode(0);
            rr.setMessage("消息发送成功");
        } catch (APIConnectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (APIRequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rr;


    }

    /**
     * 创建群组
     */
    @RequestMapping("/addgroup")
    @ResponseBody
    public ResultString<Void> addgroup(String owner, String gname, String desc) {
        ResultString<Void> rr = new ResultString<>();
        try {
            CreateGroupResult result = client.createGroup(owner, gname, desc, null, 1, owner);
            Group group = new Group();
            group.setDescr(desc);
            group.setMaxmembercount(500);
            group.setGroupid(result.getGid().toString());
            group.setOwnerusername(owner);
            group.setName(result.getName());
            group.setCtime(TimeTools.getStringBySDate(new Date()));
            group.setMtime(TimeTools.getStringBySDate(new Date()));
            groupService.save(group);
            System.out.println(result);
            rr.setCode(0);
            rr.setMessage("创建群组成功!!");
        } catch (APIConnectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (APIRequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rr;


    }

    /**
     * 修改密码
     */
    @RequestMapping("/changepwd")
    @ResponseBody
    public ResultString<Void> changepwd(String username, String pwd) {
        ResultString<Void> rr = new ResultString<>();
        User user = userService.finduserbyusername(username);

        if (user != null) {


            user.setPassword(pwd);
            userService.save(user);
            rr.setCode(0);
            rr.setMessage("修改密码成功!");

        } else {
            rr.setCode(1);
            rr.setMessage("用户名错误!!");
        }

        return rr;


    }


    /**
     * 发送群聊消息
     */
    @RequestMapping("/sendgroupmessage")
    @ResponseBody
    public ResultString<Void> sendgroupmessage(String text, String groupid, String username) {
        MessageBody body = MessageBody.text(text);
        ResultString<Void> rr = new ResultString<>();
        try {
            SendMessageResult result = client.sendGroupTextByAdmin(groupid, username, body);
            System.out.println(result);
            rr.setCode(0);
            rr.setMessage("发送群聊消息成功!!!");
        } catch (APIConnectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (APIRequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rr;

    }

    /**
     * 保存用户信息
     */
    @RequestMapping("/saveuser")
    public ResultString<Void> saveuser(String username, String sex, String nickname, String phone, String age) {
        ResultString<Void> rr = new ResultString<>();
        User user = null;
        System.out.println(age);
        System.out.println(sex);

        user = userService.finduserByPhone(phone);
        if (user == null) { //说明这个用户没有保存手机号是用户名注册的
            user = userService.finduserbyusername(username);
            user.setAge(Integer.parseInt(age));
            user.setNickname(nickname);
            user.setPhone(phone);
            user.setSex(sex);

        } else { //用户不等于空说明用户是手机号注册的

            user.setAge(Integer.parseInt(age));
            user.setNickname(nickname);

            user.setSex(sex);


        }


        userService.save(user);
        try {
            UserInfoResult res = client.getUserInfo(user.getUsername());
            client.updateUserInfo(res.getUsername(), res.getNickname(), null, null, 0, null, null, age);
        } catch (APIConnectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (APIRequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        rr.setMessage("保存用户信息成功");
        return rr;

    }

    /**
     * 获取所有的专家
     */
    @RequestMapping("/getallexperts")
    @ResponseBody
    public ResultString<String> findallexperts() {
        ResultString<String> rr = new ResultString<>();
        List<User> list = userService.finduserbytype();

        JsonArray json = new JsonArray();
        for (int i = 0; i < list.size(); i++) {
            JsonObject jsonobj = new JsonObject();
            jsonobj.addProperty("username", list.get(i).getUsername());
            jsonobj.addProperty("image", list.get(i).getImage());

            jsonobj.addProperty("phone", list.get(i).getPhone());

            System.out.println(json);
            json.add(jsonobj);

        }
        System.out.println(json);
        rr.setCode(0);
        rr.setData(json.toString());
        rr.setMessage("获取专家成功");
        return rr;


    }

    /**
     * 发送手机验证码
     */
    @RequestMapping("/sendsms")
    public ResultString<Void> sendsms(String phone) {
        ResultString<Void> rr = new ResultString<>();
        Integer m = (int) ((Math.random() * 9 + 1) * 100000);
        String content = prefix + m.toString() + suffix;
        try {
            SmsClientSend.sendSms(url, userid, account, password, phone, content);
            Jedis jedis = new Jedis("localhost");
            jedis.set(phone, m.toString());
            jedis.expire(phone, 60);
            rr.setCode(0);
            rr.setMessage("验证码发送成功!");

        } catch (Exception e) {
            // TODO: handle exception
            rr.setCode(1);
            rr.setMessage("发送验证码失败!!!");
        }


        return rr;


    }


    /**
     * 手机号注册
     */
    @RequestMapping("/phoneregister")
    public ResultString<String> sms(String phone, String code, String password) {
        Jedis jedis = new Jedis("localhost");
        ResultString<String> rr = new ResultString<>();
        User user = userService.finduserByPhone(phone);
        if (user == null) {//注册
            String m = jedis.get(phone);
            if (m == null) {
                rr.setCode(1);
                rr.setMessage("验证码错误!!!");
                return rr;
            } else {
                if (m.equals(code)) {//取出redis里的验证码
                    User user1 = new User();
                    user1.setPhone(phone);
                    /*String username=UUID.randomUUID().toString().replaceAll("-", "");*/
                    //将手机号当做用户名
                    user1.setUsername(phone);
                    user1.setPassword(password);

                    List<RegisterInfo> users = new ArrayList<RegisterInfo>();
                    RegisterInfo register = RegisterInfo.newBuilder().setUsername(phone).setPassword("123456").build();

                    try {
                        ResponseWrapper res =
                                userclient.registerAdmins(register);
                        users.add(register);
                        userService.save(user1);
                        rr.setCode(0);
                        rr.setMessage("注册成功!!!");
                        jedis.del(phone);//将key删除注册成功后
                    } catch (APIConnectionException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (APIRequestException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                } else {
                    rr.setCode(1);
                    rr.setMessage("验证码错误");


                }


            }

        } /*else { //如果用户名存在就是登陆
		
		
		String m=	jedis.get(phone);
		if(m==null) {
			rr.setCode(1);
			rr.setMessage("请您发送验证码!");
		}else {
		if(m.equals(code)) {
			jedis.del(phone);//将验证码删除
			rr.setCode(0);
			rr.setMessage("123456");
			
			rr.setMessage("验证码正确登陆成功!");
		}else {
			rr.setCode(1);
			rr.setMessage("验证码错误!!");	
		}
		
		
		
		
	}
		
		
		
		
		
		*//**
         * 手机登陆 注册 一个接口  需要手机号 验证码
         *
         *//*
			
	
	
	
	
	
		
		
		
		
		
	} */

        return rr;

    }


    @RequestMapping("/codelogin")
    public ResultString<String> codelogin(String phone, String code) {
        ResultString<String> rr = new ResultString<>();
        User user = userService.finduserByPhone(phone);
        if (user == null) {
            rr.setCode(1);
            rr.setMessage("手机号还没有注册,请您先注册!!");
        } else {
            Jedis jedis = new Jedis("localhost");
            String m = jedis.get(phone);
            if (m == null) {
                rr.setCode(1);
                rr.setMessage("请您发送验证码!");
            } else {
                if (m.equals(code)) {
                    jedis.del(phone);//将验证码删除
                    rr.setCode(0);


                    rr.setMessage("验证码正确登陆成功!");
                } else {
                    rr.setCode(1);
                    rr.setMessage("验证码错误!!");
                }
            }
        }


        return rr;


    }


    @RequestMapping("/forgetpassword")
    public ResultString<String> forgetpassword(String phone, String code) {
        //发送验证码
        //往redis里存验证码
        //用户获得验证码
        //用户传入手机号 和验证码
        //比较 验证码 和redis里的验证码
        ResultString<String> rr = new ResultString<>();
        Jedis jedis = new Jedis("localhost");
        String m = jedis.get(phone);
        if (m == null) {
            rr.setCode(1);
            rr.setMessage("请发送验证码");
            return rr;
        }
        if (m.equals(code)) {
            rr.setCode(0);
            rr.setMessage("验证码正确");
            rr.setData(userService.finduserByPhone(phone).getPassword());
        }

        return rr;

    }

}