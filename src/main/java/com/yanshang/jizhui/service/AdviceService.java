package com.yanshang.jizhui.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.yanshang.jizhui.bean.Advice;


public interface AdviceService {
    void save(Advice advice);

    void del(Integer id);

    Page<Advice> findall(String code, PageRequest pagerequest);
}
