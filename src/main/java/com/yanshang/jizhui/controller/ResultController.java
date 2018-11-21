package com.yanshang.jizhui.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.yanshang.jizhui.bean.Check;
import com.yanshang.jizhui.respository.CheckRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yanshang.jizhui.bean.Advice;
import com.yanshang.jizhui.bean.Result;
import com.yanshang.jizhui.respository.AdviceRepository;
import com.yanshang.jizhui.service.ResultService;
import com.yanshang.jizhui.util.ResultString;
import com.yanshang.jizhui.util.TimeTools;


@RestController
public class ResultController {
    @Resource
    private ResultService resultService;
    @Resource
    private AdviceRepository adviceRepository;
    @Resource
    private CheckRepository checkRepository;

    private static final int MAX_INPUT_SIZE = 5;

    private static final int MAX_HAS_PROBLEM_SIZE = 2;



    /**
     * 解析上传数据
     * @param data
     * @param username
     */
    private List<Result> resolver(String data,String username,String checkTime) {
        data = data.replace("    ", "").trim();
        String ex = data.split("Exame")[0].replaceAll("    ", "");
        String ex1 = ex.replaceAll(":", "").trim();
        String[] ex3 = ex1.split("Value");
        for (int i = 0; i < ex3.length; i++) {
            char c = ex3[i].charAt(0);
            System.out.println(c);
        }
        // 用户脊柱信息填入对象
        Result result = new Result();
        result.setSensorID01(String.valueOf(ex3[1].charAt(0)));
        result.setSensorID02(String.valueOf(ex3[2].charAt(0)));
        result.setSensorID03(String.valueOf(ex3[3].charAt(0)));
        result.setSensorID04(String.valueOf(ex3[4].charAt(0)));
        result.setSensorID05(String.valueOf(ex3[5].charAt(0)));
        result.setSensorID06(String.valueOf(ex3[6].charAt(0)));
        result.setSensorID07(String.valueOf(ex3[7].charAt(0)));
        result.setSensorID08(String.valueOf(ex3[8].charAt(0)));
        result.setSensorID09(String.valueOf(ex3[9].charAt(0)));
        result.setSensorID10(String.valueOf(ex3[10].charAt(0)));
        result.setResult(result.getResult());
        result.setUsername(username);
        result.setCreatetime(checkTime);
        System.out.println(result.getResult());
        // 数据信息入库
        resultService.save(result);
        /**
         * 旧注释：
         * 从数据库查找 用户的20条数据
         */
        // 获取刚添加的数据
        return resultService.findresultByusername(result.getUsername(), result.getCreatetime());

    }

    /**
     * 获取脊柱检查数据
     * @param message 脊柱数据信息
     * @param username 用户名
     * @param request
     * @return
     */
    @RequestMapping("/add")
    public ResultString<Map<String, Object>> save(String message, String username, HttpServletRequest request) {
        ResultString<Map<String, Object>> rr = new ResultString<>();
        Map<String, Object> messageData = new HashMap<>();
        rr.setData(messageData);
        // 解析上传数据,并获取已经上传的数据信息
        String checkTime = TimeTools.getStringBySDate(new Date());
        List<Result> list = resolver(message, username,checkTime);

        int count = 1;
        // 重复数据集合
        List<String> repeated = new ArrayList<String>();
        Result res = null;
        System.out.println(list.size());
        Map<String, Integer> map = new HashMap<String, Integer>();
        if (list.size() < MAX_INPUT_SIZE) {
            rr.setCode(1);
            rr.setData(null);
            rr.setMessage("数据正在获取中...");
            System.out.println("当前正在传输" + list.size() + "条数据!!!");
        } else if (list.size() >= MAX_INPUT_SIZE) {
            // 重复的加入list2集合
            for (int i = 0; i < list.size(); i++) {
                for (int j = i; j < list.size() - 1; j++) {
                    if (list.get(i).equals(list.get(j))) {
                        repeated.add(list.get(i).getResult());
                        break;
                    }
                }
            }
            // 统计list2集合中重复数据出现次数,对应放入Map集合
            for (String obj : repeated) {
                if (map.containsKey(obj)) {
                    map.put(obj, map.get(obj).intValue() + 1);
                    count++;//存一次 value就会加1 此时value就是key在list重复的次数
                } else {
                    map.put(obj, 1);
                }
            }
            System.out.println("count=" + count);
            List<String> list3 = new ArrayList<>();
            // 迭代Map集合,重复数据出现最多的加入list3集合
            Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Entry<String, Integer> entry = it.next();
                System.out.println(count);
                System.out.println(entry.getValue());
                if (entry.getValue() == count) {
                    list3.add(entry.getKey());
                    System.out.println("key=" + entry.getKey() + "," + "value=" + entry.getValue());
                }
            }
            System.out.println(list3.size());
            if (list3.size() > 1) {
                System.out.println(list3 + "出现的次数一样多,一共出现了:" + (count + 1) + "次");
            }
            if (list3.size() == 1) {
                System.out.println("出现最多次数的是:" + list3 + ",总共出现了:" + (count + 1) + "次");
            }

            // 消息中添加最严重的提示消息
            rr.setCode(0);
            rr.setMessage("获取指导意见成功!");
            StringBuffer stringBuffer = new StringBuffer();
            /*********************检查开始*********************/

            // 脊柱节，测试号数组
            String sensorsCodes = list3.get(0);
            char[] sensorsCodeArray = sensorsCodes.toCharArray();
            boolean checkOver = false;
            String checkMessage = "";
            String checkCode = "";
            // 获取全部指导意见
            List<Advice> allOrderLevel = adviceRepository.findAllOrderLevel();
            messageData.put("message","系统还未录入指导意见！！！");
            if (allOrderLevel == null && allOrderLevel.isEmpty()) return rr;
            // 检查是否有最严重的
            boolean mostSerious = false;
            checkCode = allOrderLevel.get(0).getCode();
            checkMessage = allOrderLevel.get(0).getContent();
            for (char ant : sensorsCodeArray) {
                mostSerious = (checkCode.indexOf(ant) != -1);
                if (checkOver = mostSerious) {
                    messageData.put("message",checkMessage);
                }
            }
            // 检查是否有有问题的脊柱节，检查出有MAX_HAS_PROBLEM_SIZE个问题的脊柱节就结束
            if (!checkOver) {
                int problem = 0;
                StringBuffer problemNum = new StringBuffer();
                checkCode = allOrderLevel.get(1).getCode();
                checkMessage = allOrderLevel.get(1).getContent();
                for (int i=0; i<sensorsCodeArray.length;i++) {
                    if (problem >= MAX_HAS_PROBLEM_SIZE) {
                        String num = problemNum.substring(0, problemNum.lastIndexOf("、"));
                        checkMessage = checkMessage.replace("X",num);
                    }
                    if (checkCode.indexOf(sensorsCodeArray[i]) != -1) {
                        problem++;
                        problemNum.append((i+1)+"、");
                    }
                }
            }
            if (!checkOver) {
                checkMessage = allOrderLevel.get(2).getContent();
            }
            messageData.put("message",checkMessage);
            checkRepository.save(new Check(sensorsCodes,checkTime,checkMessage,username));
//            Advice advice = adviceRepository.findAdvice(repeated.get(0));
//            System.out.println(advice);
//            if (advice != null) {
//                rr.setCode(0);
//                rr.setMessage("获取指导意见成功!");
//                /**
//                 * 获得指导意见成功删除重复结果
//                 */
//                resultService.del1();
//                messageData.put("message", advice.getMessage());//指导意见
//                messageData.put("content", advice.getContent());//返回结果
//                rr.setData(messageData);
//            } else {
//                rr.setCode(0);
//                messageData.put("message", "你的身体良好");//指导意见
//                messageData.put("content", "你需要补水");//返回结果
//                rr.setData(messageData);
//            }
        }
//        else {
//            rr.setCode(1);
//            rr.setData(null);
//            rr.setMessage("数据正在获取中...");
//        }
        return rr;
    }
}

