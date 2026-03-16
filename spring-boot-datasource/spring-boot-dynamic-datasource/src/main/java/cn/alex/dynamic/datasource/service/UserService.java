package cn.alex.dynamic.datasource.service;

import cn.alex.dynamic.datasource.datasource.annotation.DS;
import cn.alex.dynamic.datasource.datasource.constant.DataSourceType;
import cn.alex.dynamic.datasource.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by WCY on 2022/8/31
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @DS(name = DataSourceType.PRIMARY)
    public List<Map<String, Object>> selectAllUser() {
        List<Map<String, Object>> userList = userMapper.selectAllUser();
        return userList;
    }

    @DS(name = DataSourceType.SECONDARY)
    public List<Map<String, Object>> findAllUser() {
        List<Map<String, Object>> userList = userMapper.findAllUser();
        return userList;
    }

}
