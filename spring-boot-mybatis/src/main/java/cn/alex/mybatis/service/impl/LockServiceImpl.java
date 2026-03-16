package cn.alex.mybatis.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import cn.alex.mybatis.mapper.LockMapper;
import cn.alex.mybatis.service.LockService;

/**
 * Created by WCY on 2021/8/3
 */
@Service
public class LockServiceImpl implements LockService {

    @Resource
    private LockMapper lockMapper;

}
