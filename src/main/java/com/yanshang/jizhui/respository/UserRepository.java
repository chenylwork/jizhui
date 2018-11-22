package com.yanshang.jizhui.respository;

import java.util.List;

import com.yanshang.jizhui.bean.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.yanshang.jizhui.bean.User;


public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    /**
     * 根据用户账户获取用户信息
     * @param username
     * @return
     */
    @Query(value = "select * from t_user where username=?1", nativeQuery = true)
    User finduser(String username);

    @Query(value = "select* from t_user where rolename=?1", nativeQuery = true)
    List<User> findAll1(String rolename);

    @Query(value = "select* from t_user where rolename='专家'", nativeQuery = true)
    List<User> finduserbytype();

    @Query(value = "select * from t_user where phone=?1", nativeQuery = true)
    User findUserByPhone(String phone);

}
