package com.yanshang.jizhui.respository;

import com.yanshang.jizhui.bean.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
 * @ClassName CheckRepository
 * @Description 检查结束数据操作
 * @Author 陈彦磊
 * @Date 2018/11/21 - 13:09
 * @Version 1.0
 **/
public interface CheckRepository extends JpaRepository<Check,Integer>,JpaSpecificationExecutor<Check> {

    @Query(value = "SELECT * FROM t_check WHERE username = ?1",nativeQuery = true)
    List<Check> findAllByUserName(String username);
}
