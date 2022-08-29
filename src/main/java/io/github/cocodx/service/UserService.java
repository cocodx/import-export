package io.github.cocodx.service;

import io.github.cocodx.entity.User;

import java.util.List;

/**
* @author amazfit
* @description 针对表【t_user】的数据库操作Service
* @createDate 2022-08-30 04:25:27
*/
public interface UserService {

    List<User> findList();
}
