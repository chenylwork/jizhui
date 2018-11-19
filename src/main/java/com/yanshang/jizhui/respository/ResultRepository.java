package com.yanshang.jizhui.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.yanshang.jizhui.bean.Result;


public interface ResultRepository extends JpaRepository<Result, Integer>, JpaSpecificationExecutor<Result> {
    @Query(value = "select * from t_result where username=?1 and createtime=?2", nativeQuery = true)
    List<Result> findresultByusername(String username, String createtime);

    @Modifying
    @Query(value = "DELETE FROM t_result\r\n" +
            "WHERE id not IN (select id from (SELECT max(id) id FROM t_result t2 GROUP BY username,result,createtime) e);", nativeQuery = true)
    void del1();


}
