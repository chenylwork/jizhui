package com.yanshang.jizhui.service;

import java.util.List;

import com.yanshang.jizhui.bean.Result;


public interface ResultService {
    void save(Result result);

    List<Result> findresultByusername(String username, String createtime);

    void del(Result result);

    void del1();

    void delete(Integer id);
}
