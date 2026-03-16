package cn.alex.mybatis.plus.controller;

import cn.alex.mybatis.plus.domain.User;
import cn.alex.mybatis.plus.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by WCY on 2021/8/19
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("findUserByName")
    public List<User> findUserByName(@RequestParam(value = "name") String name) {
        return userService.selectByName(name);
    }
}
