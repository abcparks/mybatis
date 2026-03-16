package cn.alex.multiple.datasource.mapper.secondary;

import cn.alex.multiple.datasource.domain.Teacher;

import java.util.List;

/**
 * Created by WCY on 2022/9/3
 */
public interface TTeacherMapper {

    int insertTeacher(Teacher teacher);

    Long getMybatisSequence();

    int batchInsertTeacher(List<Teacher> teacherList);

    int batchInsertTeacherPrepared(List<Teacher> teacherList);

}
