package com.dawn.dawnblogback.controller;


import com.dawn.dawnblogback.pojo.EmailDTO;
import com.dawn.dawnblogback.pojo.Result;
import com.dawn.dawnblogback.pojo.User;
import com.dawn.dawnblogback.pojo.UserDTO;
import com.dawn.dawnblogback.service.EmailService;
import com.dawn.dawnblogback.service.UserService;
import com.dawn.dawnblogback.util.JwtUtil;
import com.dawn.dawnblogback.util.Md5Util;
import com.dawn.dawnblogback.util.RandomUtil;
import com.dawn.dawnblogback.util.ThreadLocalUtil;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: UserController
 * Package: com.dawn.dawnblogback.controller
 * Description:
 *
 * @Author yrx
 * @Create 2024/1/31 21:15
 * @Version 1.0
 */
@RestController
public class UserController {
    @Autowired
    private EmailService emailService;
    @Autowired(required = false)
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;

    /**
     * 发送验证码
     * @param email 路径参数
     * @return      0
     */
    @PostMapping("/code")
    public Result<String> sendRegisterCode(@RequestParam @Email String email){
        EmailDTO emailDTO = new EmailDTO();
        // 设置验证码
        String code =  RandomUtil.getVeritifacationCode();
        String content = "注册验证码为 " + code + " ，五分钟有效，请妥善保管！";
        // 邮件内容
        emailDTO.setEmail(email);
        emailDTO.setTitle("东方既白博客系统");
        emailDTO.setContent(content);
        emailService.sendMsg(emailDTO);
        // 往Redis中存储一个键值对
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(emailDTO.getEmail(), code, 300, TimeUnit.SECONDS);
        return Result.success("发送成功！");
    }

    @PostMapping("/user/register")
    public Result<String> register(@RequestBody @Validated UserDTO userDTO){
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        if (!userDTO.getCode().equals(operations.get(userDTO.getEmail()))){
            return Result.error("注册失败！");
        }
        // 查询用户
        User user = userService.findByUserEmail(userDTO.getEmail());
        if (user == null){
            // 用户名没有被占用，注册
            userDTO.setPassword(Md5Util.getMD5String(userDTO.getPassword()));
            userService.register(userDTO);
            operations.getOperations().delete(userDTO.getEmail());
            return Result.success("注册成功！");
        } else {
            // 用户名被占用
            return Result.error("用户名被占用！");
        }
    }

    @PostMapping("/user/login")
    public Result<String> login(String email, String password){
        // 根据邮箱查询User
        User user = userService.findByUserEmail(email);
        // 判断是否查询到
        if (user != null){
            if(Md5Util.checkPassword(password, user.getPassword())){
                // 登录成功
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", user.getId());
                claims.put("username", user.getUsername());
                String token = JwtUtil.genToken(claims);
                return Result.success(token);
            }
            return Result.error("密码错误！");
        } else {
            return Result.error("用户未注册！");
        }
    }

    @GetMapping("/user/info")
    public Result<User> getUserInfo(){
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer)map.get("id");
        User user = userService.findByUserId(id);
        return Result.success(user);
    }

    @PutMapping("/user/update")
    public Result updateUserInfo(){

        return Result.success();
    }
}
