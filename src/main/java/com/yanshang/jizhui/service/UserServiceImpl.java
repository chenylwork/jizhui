package com.yanshang.jizhui.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.yanshang.jizhui.bean.Advice;
import com.yanshang.jizhui.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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
    public Page<User> pageFindExpert(User user,PageRequest pagerequest) {
        Page<User> pageList = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // TODO Auto-generated method stub
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(root.get("rolename").as(String.class),"专家"));
                if (StringUtil.isNotEmpty(user.getNickname())) {
                    //按昵称模糊查询
                    predicates.add(cb.like(root.get("nickname").as(String.class), "%" + user.getNickname() + "%"));
                }
                if (StringUtil.isNotEmpty(user.getNickname())) {
                    // 按电话查询
//                    predicates.add(cb.like(root.get("nickname").as(String.class), "%" + user.getNickname() + "%"));
                    predicates.add(cb.equal(root.get("phone").as(String.class),user.getPhone()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pagerequest);
        return pageList;
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
