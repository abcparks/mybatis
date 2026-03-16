package cn.alex.multiple.datasource.mapper.primary;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by WCY on 2022/9/1
 */
public interface UserMapper {

    @Select(" select * from t_user ")
    List<Map<String, Object>> findAllUser();

}
