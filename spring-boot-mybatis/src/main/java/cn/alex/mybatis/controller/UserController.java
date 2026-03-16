package cn.alex.mybatis.controller;

import cn.alex.mybatis.domain.User;
import cn.alex.mybatis.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by WCY on 2021/8/2
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("findAllUser")
    public List<User> findAllUser(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userService.findAllUser();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo.getList();
    }

}
