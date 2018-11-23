package com.yanshang.jizhui.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yanshang.jizhui.bean.User;
import com.yanshang.jizhui.respository.UserRepository;
import com.yanshang.jizhui.service.UserService;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jmessage.api.JMessageClient;

@Controller

public class PCController {
    private static String appkey = "72ea33c57d792065597bfef0";
    private static String masterSecret = "1cd07f744ff937afd8d949b1";
    JMessageClient client = new JMessageClient(appkey, masterSecret);
    @Resource
    private UserService userService;
    @Resource
    private UserRepository userRepository;

    @RequestMapping("/login1")
    public String login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        if (userService.finduserbyusername(username) == null) {
            map.put("success", false);
            map.put("message", "用户名不存在!");
        } else {
            //只有admin才可以登陆
            if (userService.finduserbyusername(username).getPassword().equals(password) &&
                    userService.finduserbyusername(username).getUsername().equals("admin")) {
                return "index.html";
            } else {
                map.put("message", "密码错误!!!");
            }
        }
        return "error.html";
    }

    @RequestMapping("/main1")
    public String index5() {
        return "log";
    }

    @RequestMapping("/loginadmin")
    public String index() {
        return "log";
    }

    @RequestMapping("/frame")
    public String mainhtml() {
        return "main";
    }

    @RequestMapping("/advice")
    public String advice() {
        return "advice";
    }

    @RequestMapping("/uploadimg")
    public String upload() {
        return "upload";
    }

    @RequestMapping("/getuser")
    public String user() {
        return "user";
    }

    @RequestMapping("/getgroup")
    public String group() {
        return "group";
    }

    @ResponseBody
    @RequestMapping("/allusers")
    public Map<String, Object> findall() {
        Map<String, Object> map = new HashMap<>();
        List<User> list = userRepository.findAll1("专家");
        map.put("rows", list);
        map.put("total", list.size());
        return map;
    }

    @ResponseBody
    @RequestMapping("/deluser")
    public Map<String, Object> deluser(@RequestParam("ids") String ids) {
        Map<String, Object> map = new HashMap<>();
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            try {
                client.deleteUser(userRepository.getOne(Integer.parseInt(idsStr[i])).getUsername());
                userService.del(Integer.parseInt(idsStr[i]));
                map.put("success", true);
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (APIConnectionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (APIRequestException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return map;
    }

    @RequestMapping("/experts")
    @ResponseBody
    public Map<String, Object> getexperts(String username) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(username);
        User user = userService.finduserbyusername(username);
        if (user == null) {
            map.put("message", "账号不存在!");
            map.put("success", true);
        } else if (user.getRolename().equals("专家")) {
            map.put("message", "此账号存在!");
            map.put("data",user);
            map.put("success", false);
        }
        return map;
    }
}
