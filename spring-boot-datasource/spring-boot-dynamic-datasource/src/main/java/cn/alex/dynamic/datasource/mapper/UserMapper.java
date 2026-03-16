package cn.alex.dynamic.datasource.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by WCY on 2022/8/31
 */
public interface UserMapper {

    @Select(" select * from t_user ")
    List<Map<String, Object>> selectAllUser();

    List<Map<String, Object>> findAllUser();
}
