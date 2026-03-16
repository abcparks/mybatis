package cn.alex.mybatis.plus.service.impl;

import cn.alex.mybatis.plus.domain.User;
import cn.alex.mybatis.plus.mapper.UserMapper;
import cn.alex.mybatis.plus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by WCY on 2021/4/5
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> selectByName(String name) {
        return userMapper.selectByName(name);
    }

}
