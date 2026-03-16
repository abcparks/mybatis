package cn.alex.mybatis.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import cn.alex.mybatis.mapper.KeyMapper;
import cn.alex.mybatis.service.KeyService;

/**
 * Created by WCY on 2021/8/3
 */
@Service
public class KeyServiceImpl implements KeyService {

    @Resource
    private KeyMapper keyMapper;

}
