package cn.alex.dynamic.datasource.service;

import cn.alex.dynamic.datasource.datasource.annotation.DS;
import cn.alex.dynamic.datasource.datasource.constant.DataSourceType;
import cn.alex.dynamic.datasource.domain.Teacher;
import cn.alex.dynamic.datasource.mapper.primary.TeacherMapper;
import cn.alex.dynamic.datasource.mapper.secondary.TTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by WCY on 2022/9/4
 */
@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TTeacherMapper tTeacherMapper;

    @Autowired
    private TeacherService teacherService;

    public void insertTeacher(Teacher teacher) {
        teacherService.mysqlInsertTeacher(teacher);
        teacherService.oracleInsertTeacher(teacher);
    }

    @DS(name = DataSourceType.PRIMARY)
    @Transactional(rollbackFor = Exception.class)
    public void mysqlInsertTeacher(Teacher teacher) {
        teacherMapper.insertTeacher(teacher);
        int result = 1 / 0;
    }

    @DS(name = DataSourceType.SECONDARY)
    @Transactional(rollbackFor = Exception.class)
    public void oracleInsertTeacher(Teacher teacher) {
        tTeacherMapper.insertTeacher(teacher);
        int result = 1 / 0;
    }

}
