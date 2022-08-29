package io.github.cocodx.controller;

import io.github.cocodx.entity.User;
import io.github.cocodx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author amazfit
 * @date 2022-08-30 上午4:01
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public List<User> findList(){
        return userService.findList();
    }
}
