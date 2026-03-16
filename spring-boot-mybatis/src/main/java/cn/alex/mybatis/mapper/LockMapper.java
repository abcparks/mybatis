package cn.alex.mybatis.mapper;

import cn.alex.mybatis.domain.Lock;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by WCY on 2021/8/3
 */
public interface LockMapper {
    Lock getById(@Param("id") Integer id);

    Lock getLockById(@Param("id") Integer id);

    List<Lock> getLock();

    Lock getLockByIdByStep(@Param("id") Integer id);

    List<Map<String, Object>> getOne2Many();
}
