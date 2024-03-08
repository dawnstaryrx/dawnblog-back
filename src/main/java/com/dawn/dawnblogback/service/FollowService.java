package com.dawn.dawnblogback.service;

import com.dawn.dawnblogback.pojo.User;

import java.util.List;

public interface FollowService {
    void add(Integer followUserId);

    void delete(Integer followUserId);

    Integer isFollowing(Integer userId, Integer followUserId);

    List<User> fanList(Integer userId);

    List<User> followList(Integer userId);
}
