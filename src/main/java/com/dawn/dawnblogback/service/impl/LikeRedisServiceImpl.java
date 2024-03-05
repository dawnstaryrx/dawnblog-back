package com.dawn.dawnblogback.service.impl;

import com.dawn.dawnblogback.pojo.dto.UserLikeCountDTO;
import com.dawn.dawnblogback.pojo.dto.UserLikeDTO;
import com.dawn.dawnblogback.service.LikeRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeRedisServiceImpl implements LikeRedisService {
//    @Autowired
//    private HashOperations<String, String, Object> redisHash;// Redis Hash
    @Override
    public Integer getLikeStatus(String infoId, String likeUserId) {
        return null;
    }

    @Override
    public void saveLiked2Redis(String infoId, String likeUserId) {

    }

    @Override
    public void unlikeFromRedis(String infoId, String likeUserId) {

    }

    @Override
    public void deleteLikedFromRedis(String infoId, String likeUserId) {

    }

    @Override
    public void in_decrementLikedCount(String infoId, Integer delta) {

    }

    @Override
    public List<UserLikeDTO> getLikedDataFromRedis() {
        return null;
    }

    @Override
    public List<UserLikeCountDTO> getLikedCountFromRedis() {
        return null;
    }
}
