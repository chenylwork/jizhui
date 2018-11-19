package com.yanshang.jizhui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TestController {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        Map<String, Integer> map = new HashMap<String, Integer>();

        list.add("abc");
        list.add("abc");
        list.add("abc");
        list.add("abc");
        list.add("abc");
        list.add("abc");
        list.add("abc");
        list.add("abc");
        list.add("abc");
        list.add("abc");
        list.add("abc");
        list.add("abc");
        list.add("jdk");

        // list.add(77);
        // list.add(77);
        // list.add(77);
        int count = 1;

        //重复的加入list2集合
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    list2.add(list.get(i));
                    break;
                }
            }
        }

        //遍历list2


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
        list2.clear();
        Integer value1 = map.get("abc");
        System.out.println(value1);
        for (Integer value : map.values()) {
            System.out.println("Value = " + value);
        }

        //迭代Map集合,重复数据出现最多的加入list3集合
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Integer> entry = it.next();
            System.out.println(count);
            System.out.println(entry.getValue());
            if (entry.getValue() == count) {
                list2.add(entry.getKey());
                System.out.println("key=" + entry.getKey() + "," + "value=" + entry.getValue());
            }
        }
        System.out.println(list2.size());
        if (list2.size() > 1) {
            System.out.println(list2 + "出现的次数一样多,一共出现了:" + (count + 1) + "次");
        }
        if (list2.size() == 1) {
            System.out.println("出现最多次数的是:" + list2 + ",总共出现了:" + (count + 1) + "次");
        }

    }


}



