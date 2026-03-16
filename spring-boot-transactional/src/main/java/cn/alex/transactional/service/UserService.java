package cn.alex.transactional.service;

import cn.alex.transactional.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by WCY on 2022/5/16
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public void findAllUser() {
        List<Map<String, Object>> allUser = userMapper.findAllUser();
        System.out.println("allUser = " + allUser);

        userMapper.updateUser();

        int a = 1 / 0;
    }

}
