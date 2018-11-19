package com.yanshang.jizhui.service;

import java.util.List;

import com.yanshang.jizhui.bean.User;


public interface UserService {
    void save(User user);

    void del(Integer id);

    User findUser(Integer id);

    User finduserbyusername(String username);

    List<User> finduserbytype();

    User finduserByPhone(String phone);
}
