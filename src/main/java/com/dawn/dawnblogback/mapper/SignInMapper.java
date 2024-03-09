package com.dawn.dawnblogback.mapper;

import com.dawn.dawnblogback.pojo.SignIn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SignInMapper {
    @Select("select * from sign_in where user_id = #{userId}")
    SignIn getSignInByUserId(Integer userId);
    @Insert("insert into sign_in(user_id,  update_time) values (#{userId}, now())")
    void add(Integer userId);
    @Update("update sign_in set continue_days = #{continueDays}, update_time = now() where id = #{id}")
    void update(SignIn signIn);
}
