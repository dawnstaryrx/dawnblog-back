package com.dawn.dawnblogback;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: RedisTest
 * Package: com.dawn.dawnblogback
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/1 7:27
 * @Version 1.0
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet(){
        // 往Redis中存储一个键值对
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("id", "1", 15, TimeUnit.SECONDS);

    }
    @Test
    public void testGet(){
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        System.out.println(operations.get("username"));
        System.out.println(operations.get("id"));
    }
}
