package com.yanshang.jizhui.service;

import java.util.List;

import com.yanshang.jizhui.bean.Group;

public interface GroupService {
    Group findgroupbyid(Integer id);

    List<Group> findAll();

    void del(Integer id);

    /**
     * 根据groupid删除群组
     * @param groupID
     * @return
     */
    int delByGroupId(String groupID);

    void save(Group group);
}
