package com.yanshang.jizhui.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yanshang.jizhui.bean.Group;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GroupRepository extends JpaRepository<Group, Integer>, JpaSpecificationExecutor<Group> {
    /**
     *
     * @param groupId
     * @return
     */
    @Modifying
    @Query(value = "DELETE from t_group where groupid = ?1",nativeQuery = true)
    int deleteGroupByGroupid(String groupId);

}
