package com.dawn.dawnblogback.controller;

import com.dawn.dawnblogback.pojo.Result;
import com.dawn.dawnblogback.pojo.User;
import com.dawn.dawnblogback.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FollowController {
    @Autowired
    private FollowService followService;

    @PutMapping("/follow")
    public Result add(@RequestParam Integer followUserId){
        followService.add(followUserId);
        return Result.success();
    }
    @DeleteMapping("/follow")
    public Result delete(@RequestParam Integer followUserId){
        followService.delete(followUserId);
        return Result.success();
    }

    // 判断user是否正在关注followUser
    @PostMapping("/follow")
    public Result isFollowing(@RequestParam Integer userId, Integer followUserId){
        Integer num = followService.isFollowing(userId, followUserId);
        if (num == 1)
            return Result.success(1);
        else
            return Result.success(0);
    }
    @GetMapping("/follow/fan")
    public Result<List<User>> fanList(@RequestParam Integer userId){
        List<User> list = followService.fanList(userId);
        return Result.success(list);
    }
    @GetMapping("/follow/follow")
    public Result<List<User>> followList(@RequestParam Integer userId){
        List<User> list = followService.followList(userId);
        return Result.success(list);
    }
}
