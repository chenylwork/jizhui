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

    @RequestMapping("/add")
    public ResultString<Map<String, Object>> save(String message, String username, HttpServletRequest request) {
        ResultString<Map<String, Object>> rr = new ResultString<>();
        Map<String, Object> map1 = new HashMap<>();


        message = message.replace("    ", "").trim();


        String ex = message.split("Exame")[0].replaceAll("    ", "");
        String ex1 = ex.replaceAll(":", "").trim();
        String[] ex3 = ex1.split("Value");

        for (int i = 0; i < ex3.length; i++) {

            char c = ex3[i].charAt(0);
            System.out.println(c);

        }
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
        result.setCreatetime(TimeTools.getStringBySDate(new Date()));
        System.out.println(result.getResult());
        resultService.save(result);
        /**
         * 从数据库查找 用户的20条数据
         */
        List<Result> list = resultService.findresultByusername(result.getUsername(), result.getCreatetime());
        int count = 1;
        List<String> list2 = new ArrayList<String>();
        Result res = null;
        System.out.println(list.size());
        Map<String, Integer> map = new HashMap<String, Integer>();

        if (list.size() < 10) {
            System.out.println("当前正在传输" + list.size() + "条数据!!!");
        } else if (list.size() >= 10) {

            //重复的加入list2集合
            for (int i = 0; i < list.size(); i++) {
                for (int j = i; j < list.size() - 1; j++) {
                    if (list.get(i).equals(list.get(j))) {
                        list2.add(list.get(i).getResult());
                        break;
                    }
                }
            }

            //统计list2集合中重复数据出现次数,对应放入Map集合
            for (String obj : list2) {
                if (map.containsKey(obj)) {


                    map.put(obj, map.get(obj).intValue() + 1);
                    count++;//存一次 value就会加1 此时value就是key在list重复的次数
                } else {
                    map.put(obj, 1);
                }
            }
            System.out.println("count=" + count);

            List<String> list3 = new ArrayList<>();

            //迭代Map集合,重复数据出现最多的加入list3集合
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


            Advice advice = adviceRepository.findAdvice(list2.get(0));
            System.out.println(advice);
            if (advice != null) {
                rr.setCode(0);
                rr.setMessage("获取指导意见成功!");
                /**
                 * 获得指导意见成功删除重复结果
                 */


                resultService.del1();
                map1.put("message", advice.getMessage());//指导意见
                map1.put("content", advice.getContent());//返回结果


                rr.setData(map1);
            } else {
                rr.setCode(0);
                map1.put("message", "你的身体良好");//指导意见
                map1.put("content", "你需要补水");//返回结果
                rr.setData(map1);
            }


        } else {
            rr.setCode(1);
            rr.setData(null);
            rr.setMessage("数据正在获取中...");
        }


        return rr;

    }
}
