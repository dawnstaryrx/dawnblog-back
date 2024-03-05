package com.dawn.dawnblogback.mapper;

import com.dawn.dawnblogback.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

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
    @Update("update user set username = #{name}, update_time = #{time}, introduction = #{introduction} where id = #{id}")
    void updateUsername(Integer id, String name, String introduction,LocalDateTime time);
    @Update("update user set avatar = #{avatarUrl}, update_time = now() where id = #{id}")
    void updateAvatarUrl(String avatarUrl, Integer id);
    @Update("update user set password = #{newPwd} where id = #{id}")
    void updatePwd(String newPwd, Integer id);
}
