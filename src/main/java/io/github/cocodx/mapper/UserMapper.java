package io.github.cocodx.mapper;


import io.github.cocodx.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author amazfit
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2022-08-30 04:25:27
* @Entity generator.entity.User
*/
@Mapper
public interface UserMapper {


    List<User> findList();
}
