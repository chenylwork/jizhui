package com.yanshang.jizhui.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.yanshang.jizhui.bean.Advice;


public interface AdviceRepository extends JpaRepository<Advice, Integer>, JpaSpecificationExecutor<Advice> {
    @Query(value = "select * from t_advice where code=?1", nativeQuery = true)
    Advice findAdvice(String result);

}
