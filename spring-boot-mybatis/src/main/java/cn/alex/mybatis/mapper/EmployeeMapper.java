package cn.alex.mybatis.mapper;

import cn.alex.mybatis.domain.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by WCY on 2021/8/2
 */
public interface EmployeeMapper {
    Employee selectEmpById(Integer id);

    int insertEmployee(Employee employee);

    int insertSelectKey(Employee employee);

    /**
     * 根据员工编号和员工姓名查询员工信息
     */
    Employee selectById(@Param("id") Integer id);

    List<Employee> selectAll();

    Map<String, Object> selectByIdReturnMap(@Param("id") Integer id);

    List<Map<String, Object>> selectAllReturnMap();

    /*
        @MapKey("id") 表示封装Map, 以id作为key,
        查询每条记录作为value(value为以列名作key, 以值作为value的Map)
     */
    @MapKey("email")
    Map<String, Employee> selectAllMapKey();

}
