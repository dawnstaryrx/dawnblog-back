package com.dawn.dawnblogback.interceptors;

import com.dawn.dawnblogback.util.JwtUtil;
import com.dawn.dawnblogback.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * ClassName: LoginInterceptor
 * Package: com.dawn.dawnblogback.interceptors
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/1 20:10
 * @Version 1.0
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired(required = false)
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 令牌验证
        String token = request.getHeader("Authorization");
        //验证token
        try {
            // 取redis中的token
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(token);
            if (redisToken == null){
                // token失效
                throw new RuntimeException();
            }
            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 把业务数据存储到ThreadLocal中
            ThreadLocalUtil.set(claims);
            return true; // 放行
        }catch (Exception e) {
            // http响应状态码为401
            response.setStatus(401);
            return false; // 不放行
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清空ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
