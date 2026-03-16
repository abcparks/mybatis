package cn.alex.mybatis.mapper;

import cn.alex.mybatis.domain.User;

import java.util.List;

/**
 * Created by WCY on 2021/8/2
 */
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> findAllUser();
}
