package com.yanshang.jizhui.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.yanshang.jizhui.bean.Result;
import com.yanshang.jizhui.respository.ResultRepository;


@Service
@Transactional
public class ResultServiceImpl implements ResultService {
    @Resource
    private ResultRepository resultRepository;

    @Override
    public void save(Result result) {
        // TODO Auto-generated method stub
        resultRepository.save(result);
    }

    @Override
    public List<Result> findresultByusername(String username) {
        // TODO Auto-generated method stub
        return resultRepository.findresultByusername(username);
    }

    @Override
    public void del(Result result) {
        // TODO Auto-generated method stub
        resultRepository.delete(result);
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        resultRepository.deleteById(id);
    }

    @Override
    @Modifying
    public void del1() {
        // TODO Auto-generated method stub
        resultRepository.del1();
    }

    @Override
    @Modifying
    public int update(String username) {
        return resultRepository.updateState(username);
    }
}
