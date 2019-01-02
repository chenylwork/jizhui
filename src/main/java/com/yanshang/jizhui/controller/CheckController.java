package com.yanshang.jizhui.controller;

import com.yanshang.jizhui.bean.Advice;
import com.yanshang.jizhui.bean.Check;
import com.yanshang.jizhui.respository.CheckRepository;
import com.yanshang.jizhui.util.ResultString;
import com.yanshang.jizhui.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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

    /**
     * 分页获取检查信息
     */
    @RequestMapping("/find/all/check")
    public Page<Check> findCheckByPage(@RequestParam(value = "pageNo",defaultValue = "1") String pageNo,
                                @RequestParam(value = "rows",defaultValue = "10") String rows,Check check)
    {
        PageRequest pageRequest = new PageRequest(Integer.parseInt(pageNo) - 1, Integer.parseInt(rows));
        Page<Check> pagelist = checkRepository.findAll(new Specification<Check>() {
            @Override
            public Predicate toPredicate(Root<Check> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // TODO Auto-generated method stub
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtil.isNotEmpty(check.getUsername())) {
                    //按编号模糊查询
                    predicates.add(cb.equal(root.get("username").as(String.class), check.getUsername()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageRequest);

        return pagelist;
    }


}
