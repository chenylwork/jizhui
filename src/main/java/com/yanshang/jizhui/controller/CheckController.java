package com.yanshang.jizhui.controller;

import com.yanshang.jizhui.bean.Check;
import com.yanshang.jizhui.respository.CheckRepository;
import com.yanshang.jizhui.util.ResultString;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @ClassName CheckController
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2018/11/21- 14:35
 * @Version 1.0
 **/
@RestController
public class CheckController {

    @Resource
    private CheckRepository checkRepository;

    @RequestMapping("/find/check/{name}")
    public ResultString<Map<String, Object>> findMyCheck(@PathVariable("name") String username) {
        ResultString<Map<String, Object>> resultString = new ResultString<>();
        List<Check> allCheck = checkRepository.findAllByUserName(username);
        if (allCheck == null && allCheck.isEmpty()) {
            resultString.setCode(0);
            resultString.setMessage("您还没有进行测试！！");
        } else {
            resultString.setCode(1);
            resultString.setMessage("获取成功");
            Map<String,Object> map = new HashMap<>();
            map.put("checkList",allCheck);
            resultString.setData(map);
        }
        return resultString;
    }

}
