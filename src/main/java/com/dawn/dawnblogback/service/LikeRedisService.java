package com.dawn.dawnblogback.service;

import com.dawn.dawnblogback.pojo.dto.UserLikeCountDTO;
import com.dawn.dawnblogback.pojo.dto.UserLikeDTO;

import java.util.List;

public interface LikeRedisService {
    /**
     * 获取点赞状态
     * @param infoId
     * @param likeUserId
     */
    Integer getLikeStatus(String infoId, String likeUserId);
    /**
     * 点赞。状态为1
     * @param infoId
     * @param likeUserId
     */
    void saveLiked2Redis(String infoId, String likeUserId);

    /**
     * 取消点赞。将状态改变为0
     * @param infoId
     * @param likeUserId
     */
    void unlikeFromRedis(String infoId, String likeUserId);

    /**
     * 从Redis中删除一条点赞数据
     * @param infoId
     * @param likeUserId
     */
    void deleteLikedFromRedis(String infoId, String likeUserId);

    /**
     * 该内容的点赞数变化Δdelta
     * @param infoId
     */
    void in_decrementLikedCount(String infoId, Integer delta);

    /**
     * 获取Redis中存储的所有点赞数据
     * @return
     */
    List<UserLikeDTO> getLikedDataFromRedis();

    /**
     * 获取Redis中存储的所有点赞数量
     * @return
     */
    List<UserLikeCountDTO> getLikedCountFromRedis();
}
