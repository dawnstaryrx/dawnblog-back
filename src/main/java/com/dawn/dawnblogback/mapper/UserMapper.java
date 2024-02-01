package com.dawn.dawnblogback.mapper;

import com.dawn.dawnblogback.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * ClassName: UserMapper
 * Package: com.dawn.dawnblogback.mapper
 * Description:
 *
 * @Author yrx
 * @Create 2024/1/31 21:36
 * @Version 1.0
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user(email, username, password, create_time, update_time) values (#{email}, #{username}, #{password}, now(), now())")
    void insertUser(User user);
    @Select("select * from user where email = #{email}")
    User findByUserEmail(String email);
    @Select("select * from user where id = #{id}")
    User findByUserId(Integer id);
}
