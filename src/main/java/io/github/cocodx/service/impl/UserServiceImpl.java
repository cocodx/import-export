package io.github.cocodx.service.impl;

import io.github.cocodx.entity.User;
import io.github.cocodx.mapper.UserMapper;
import io.github.cocodx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author amazfit
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-08-30 04:25:27
*/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findList() {
        return userMapper.findList();
    }
}
