package com.yanshang.jizhui.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yanshang.jizhui.bean.Group;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GroupRepository extends JpaRepository<Group, Integer>, JpaSpecificationExecutor<Group> {

    /**
     * 根据groupID删除
     * @param groupId
     * @return
     */
    int deleteByGroupid(String groupId);

}
