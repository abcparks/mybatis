package cn.alex.mybatis.mapper;

import cn.alex.mybatis.domain.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * Created by WCY on 2021/8/3
 */
public interface TeacherMapper {
    Teacher getById(@Param("id") Integer id);

    List<Teacher> getTeacherByCondition(Teacher teacher);

    List<Teacher> getTeacherByCondition2(Teacher teacher);

    List<Teacher> getTeacherByIdIn(@Param("idList") Collection<Integer> idList);

    List<Teacher> getTeacherByChoose(Teacher teacher);

    int updateBySelective(Teacher teacher);
}
