package com.dawn.dawnblogback.util;

public class RedisKeyUtil {
    // 保存用户点赞数据的key
    public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";

    // 保存用户被点赞数量的key
    public static final String MAP_KEY_USER_LIKED_COUNT = "MAP_USER_LIKED_COUNT";

    // 拼接被点赞的用户id和点赞的人的id作为key。格式 222222::333333
    public static String getLikedKey(String likedUserId, String likedPostId){
        return likedUserId +
                "::" +
                likedPostId;
    }
}
