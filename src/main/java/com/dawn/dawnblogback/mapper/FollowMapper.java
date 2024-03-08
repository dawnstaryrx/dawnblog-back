package com.dawn.dawnblogback.mapper;

import com.dawn.dawnblogback.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FollowMapper {
    @Insert("insert into follow(user_id, follow_user_id, create_time) values (#{userId}, #{followUserId}, NOW())")
    void add(Integer userId, Integer followUserId);

    @Delete("delete from follow where user_id = #{userId} and follow_user_id = #{followUserId}")
    void delete(Integer userId, Integer followUserId);

    @Select("select count(*) from follow where user_id = #{userId} and follow_user_id = #{followUserId}")
    Integer isFollowing(Integer userId, Integer followUserId);

    @Select("select user.* from user join follow on user.id = follow.user_id where follow.follow_user_id = #{userId}")
    List<User> fanList(Integer userId);
    @Select("select user.* from user join follow on user.id = follow.follow_user_id where follow.user_id = #{userId}")
    List<User> followList(Integer userId);
}
