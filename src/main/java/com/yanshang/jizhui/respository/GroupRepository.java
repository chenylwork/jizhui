package com.yanshang.jizhui.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yanshang.jizhui.bean.Group;

public interface GroupRepository extends JpaRepository<Group, Integer>, JpaSpecificationExecutor<Group> {

}
