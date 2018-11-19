package com.yanshang.jizhui.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.yanshang.jizhui.bean.Advice;
import com.yanshang.jizhui.respository.AdviceRepository;
import com.yanshang.jizhui.util.StringUtil;


@Service
public class AdviceServiceImpl implements AdviceService {
    @Resource
    private AdviceRepository adviceRepository;

    @Override
    public void save(Advice advice) {
        // TODO Auto-generated method stub
        adviceRepository.save(advice);
    }

    @Override
    public void del(Integer id) {
        // TODO Auto-generated method stub
        adviceRepository.deleteById(id);
    }

    @Override
    public Page<Advice> findall(String code, PageRequest pagerequest) {
        // TODO Auto-generated method stub
        Page<Advice> pagelist = adviceRepository.findAll(new Specification<Advice>() {

            @Override
            public Predicate toPredicate(Root<Advice> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // TODO Auto-generated method stub
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtil.isNotEmpty(code)) {
                    //按编号模糊查询
                    predicates.add(cb.like(root.get("code").as(String.class), "%" + code + "%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pagerequest);
        return pagelist;
    }

}
