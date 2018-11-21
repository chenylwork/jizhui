package com.yanshang.jizhui.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yanshang.jizhui.bean.Advice;
import com.yanshang.jizhui.service.AdviceService;


@RestController
public class AdviceController {
    @Resource
    private AdviceService adviceService;

    @RequestMapping("/alladvices")
    public Map<String, Object> alladvices(@RequestParam(value = "page", defaultValue = "1") String page, @RequestParam(value = "pagesize"
            , defaultValue = "20") String rows, String code) {
        Map<String, Object> map = new HashMap<>();
        Page<Advice> pagelist = adviceService.findall(code, new PageRequest(Integer.parseInt(page) - 1, Integer.parseInt(rows)));
        System.out.println(pagelist);

        map.put("rows", pagelist.getContent());
        map.put("total", pagelist.getSize());
        return map;
    }


    //增加一条新的通知
    @RequestMapping("/addadvice")
    public Map<String, Object> savegoods(Advice advice, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        adviceService.save(advice);
        map.put("success", true);
        return map;


    }

    //删除通知
    @RequestMapping("/deladvice")
    public Map<String, Object> del(@RequestParam(value = "ids") String ids) {
        Map<String, Object> map = new HashMap<>();
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            adviceService.del(Integer.parseInt(idsStr[i]));
        }
        map.put("success", true);
        return map;
    }
}
