package com.yanshang.jizhui.service;

import java.util.List;

import com.yanshang.jizhui.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface UserService {
    void save(User user);

    void del(Integer id);

    User findUser(Integer id);

    /**
     * 分页获取专家
     * @param user
     */
    Page<User> pageFindExpert(User user,PageRequest pagerequest);

    User finduserbyusername(String username);

    List<User> finduserbytype();

    User finduserByPhone(String phone);
}
