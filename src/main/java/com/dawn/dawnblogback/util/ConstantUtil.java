package com.dawn.dawnblogback.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantUtil  implements InitializingBean {

    @Value("${tecent.cos.file.region}")
    private String region;
    @Value("${tecent.cos.file.secretId}")
    private String secretId;
    @Value("${tecent.cos.file.secretKey}")
    private String secretKey;
    @Value("${tecent.cos.file.bucketName}")
    private String bucketName;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = region;
        ACCESS_KEY_ID = secretId;
        ACCESS_KEY_SECRET = secretKey;
        BUCKET_NAME = bucketName;
    }
}
