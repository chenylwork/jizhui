package com.yanshang.jizhui.service;

import com.yanshang.jizhui.bean.Group;
import com.yanshang.jizhui.respository.GroupRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;


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
    @Transactional
    @Override
    public int delByGroupId(String groupID) {
        return groupRepository.deleteByGroupid(groupID);
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
