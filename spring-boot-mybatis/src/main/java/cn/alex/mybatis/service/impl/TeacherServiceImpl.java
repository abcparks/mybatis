package cn.alex.mybatis.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import cn.alex.mybatis.mapper.TeacherMapper;
import cn.alex.mybatis.service.TeacherService;

/**
 * Created by WCY on 2021/8/3
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

}
