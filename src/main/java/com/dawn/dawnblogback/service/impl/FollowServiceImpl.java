package com.dawn.dawnblogback.service.impl;

import com.dawn.dawnblogback.mapper.FollowMapper;
import com.dawn.dawnblogback.pojo.User;
import com.dawn.dawnblogback.service.FollowService;
import com.dawn.dawnblogback.service.UserService;
import com.dawn.dawnblogback.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private UserService userService;
    @Override
    public void add(Integer followUserId) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        followMapper.add(userId, followUserId);
        userService.haveFollow(userId);
        userService.haveFan(followUserId);
    }

    @Override
    public void delete(Integer followUserId) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        followMapper.delete(userId, followUserId);
        userService.removeFollow(userId);
        userService.removeFan(followUserId);
    }

    @Override
    public Integer isFollowing(Integer userId, Integer followUserId) {
        Integer num = followMapper.isFollowing(userId, followUserId);
        return num;
    }

    @Override
    public List<User> fanList(Integer userId) {
        List<User> list = followMapper.fanList(userId);
        return list;
    }

    @Override
    public List<User> followList(Integer userId) {
        List<User> list = followMapper.followList(userId);
        return list;
    }
}
