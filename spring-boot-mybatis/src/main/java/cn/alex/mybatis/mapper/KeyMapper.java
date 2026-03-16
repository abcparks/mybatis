package cn.alex.mybatis.mapper;

import cn.alex.mybatis.domain.Key;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by WCY on 2021/8/3
 */
public interface KeyMapper {
    Key getById(@Param("id") Integer id);

    Key getKeyById(@Param("id") Integer id);

    Key getKeyByIdByStep(@Param("id") Integer id);

    List<Key> findKeyByLockId(@Param("lockId") Integer lockId);
}
