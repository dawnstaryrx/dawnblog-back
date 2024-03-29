package com.dawn.dawnblogback.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

/**
 * ClassName: JwtUtil
 * Package: com.dawn.dawnblogback.util
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/1 17:20
 * @Version 1.0
 */
public class JwtUtil {

    private static final String KEY = "dawnstar";

    //接收业务数据,生成token并返回
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24小时过期
//                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60)) // 1分钟过期，用于测试
                .sign(Algorithm.HMAC256(KEY));
    }

    //接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }

}
