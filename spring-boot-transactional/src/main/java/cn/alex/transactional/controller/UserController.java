package cn.alex.transactional.controller;

import cn.alex.transactional.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WCY on 2022/5/16
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("findAllUser")
    public void findAllUser() {
        userService.findAllUser();
    }
}
