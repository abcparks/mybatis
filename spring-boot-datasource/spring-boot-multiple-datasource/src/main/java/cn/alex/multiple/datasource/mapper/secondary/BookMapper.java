package cn.alex.multiple.datasource.mapper.secondary;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by WCY on 2022/8/31
 */
public interface BookMapper {

    @Select(" select * from T_BOOK ")
    List<Map<String, Object>> findAllBook();

    List<Map<String, Object>> selectAllBook();

}
