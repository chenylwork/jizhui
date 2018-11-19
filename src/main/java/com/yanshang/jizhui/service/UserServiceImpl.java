package com.yanshang.jizhui.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yanshang.jizhui.bean.User;
import com.yanshang.jizhui.respository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        // TODO Auto-generated method stub
        userRepository.save(user);
    }

    @Override
    public void del(Integer id) {
        // TODO Auto-generated method stub
        userRepository.deleteById(id);
    }

    @Override
    public User findUser(Integer id) {
        // TODO Auto-generated method stub
        return userRepository.getOne(id);
    }

    @Override
    public User finduserbyusername(String username) {
        // TODO Auto-generated method stub
        return userRepository.finduser(username);
    }

    @Override
    public List<User> finduserbytype() {
        // TODO Auto-generated method stub
        return userRepository.finduserbytype();
    }

    @Override
    public User finduserByPhone(String phone) {
        // TODO Auto-generated method stub
        return userRepository.findUserByPhone(phone);
    }

}
