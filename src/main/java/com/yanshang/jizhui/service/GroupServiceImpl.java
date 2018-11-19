package com.yanshang.jizhui.service;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.yanshang.jizhui.bean.Group;
import com.yanshang.jizhui.respository.GroupRepository;


@Service
public class GroupServiceImpl implements GroupService {
    @Resource
    private GroupRepository groupRepository;

    @Override
    public void save(Group group) {
        // TODO Auto-generated method stub
        System.out.println(group);
        groupRepository.save(group);
    }

    @Override
    public void del(Integer id) {
        // TODO Auto-generated method stub
        groupRepository.deleteById(id);
    }

    @Override
    public List<Group> findAll() {
        // TODO Auto-generated method stub
        return groupRepository.findAll();
    }

    @Override
    public Group findgroupbyid(Integer id) {
        // TODO Auto-generated method stub
        return groupRepository.getOne(id);
    }

}
