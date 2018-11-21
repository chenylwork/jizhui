package com.yanshang.jizhui.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.yanshang.jizhui.bean.Advice;

import java.util.List;


public interface AdviceRepository extends JpaRepository<Advice, Integer>, JpaSpecificationExecutor<Advice> {
    /**
     * 获取指导意见
     * @param result
     * @return
     */
    @Query(value = "select * from t_advice where code=?1", nativeQuery = true)
    Advice findAdvice(String result);
    @Query(value = "SELECT * FROM t_advice ORDER BY `level` DESC",nativeQuery = true)
    List<Advice> findAllOrderLevel();

}
