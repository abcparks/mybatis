package cn.alex.multiple.datasource.mapper.primary;

import cn.alex.multiple.datasource.domain.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by WCY on 2022/9/3
 */
public interface TeacherMapper {

    int insertTeacher(Teacher teacher);

    int batchInsertTeacher(@Param("list") List<Teacher> teacherList);

}
