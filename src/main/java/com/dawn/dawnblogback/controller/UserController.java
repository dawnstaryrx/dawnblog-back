package com.dawn.dawnblogback.controller;


import com.dawn.dawnblogback.pojo.dto.EmailDTO;
import com.dawn.dawnblogback.pojo.Result;
import com.dawn.dawnblogback.pojo.User;
import com.dawn.dawnblogback.pojo.dto.UserDTO;
import com.dawn.dawnblogback.service.EmailService;
import com.dawn.dawnblogback.service.UserService;
import com.dawn.dawnblogback.util.JwtUtil;
import com.dawn.dawnblogback.util.Md5Util;
import com.dawn.dawnblogback.util.RandomUtil;
import com.dawn.dawnblogback.util.ThreadLocalUtil;
import jakarta.validation.constraints.Email;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
@Slf4j

public class UserController {
    @Autowired
    private EmailService emailService;
    @Autowired(required = false)
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;

    /**
     * 找回密码发送验证码
     * @param email 路径参数
     * @return      0
     */
    @PostMapping("/registerCode")
    public Result sendRegisterCode(@RequestParam @Email String email){
        if(Objects.equals(email, "") || email == null) {
            return Result.error("请输入邮箱！");
        }
        EmailDTO emailDTO = new EmailDTO();
        // 设置验证码
        String code =  RandomUtil.getVerifyCode();
        String content = "验证码为 " + code + " ，五分钟有效，请妥善保管！";
        // 邮件内容
        emailDTO.setEmail(email);
        emailDTO.setTitle("东方既白博客系统——注册服务");
        emailDTO.setContent(content);
        // 查询用户
        User user = userService.findByUserEmail(email);
        if (user == null){
            // 没被注册
            emailService.sendMsg(emailDTO);
            // 往Redis中存储一个键值对
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(emailDTO.getEmail(), code, 300, TimeUnit.SECONDS);
            return Result.success();
        }
        return Result.error("已被注册！");
    }

    @PostMapping("/user/register")
    public Result register(@RequestBody @Validated UserDTO userDTO){
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        if(userDTO.getEmail().length() ==0 ){
            return Result.error("请输入邮箱！");
        }
        if(userDTO.getCode().length() !=6 ){
            return Result.error("请输入6位验证码！");
        }
        if(userDTO.getPassword().length() ==0 ){
            return Result.error("请输入密码！");
        }
        if (!userDTO.getCode().equals(operations.get(userDTO.getEmail()))){
            return Result.error("注册失败！");
        }

        userDTO.setPassword(Md5Util.getMD5String(userDTO.getPassword()));
        userService.register(userDTO);
        operations.getOperations().delete(userDTO.getEmail());
        return Result.success("注册成功！");
    }

    @PostMapping("/forgetCode")
    public Result sendForgetCode(@RequestParam @Email String email){
        EmailDTO emailDTO = new EmailDTO();
        // 设置验证码
        String code =  RandomUtil.getVerifyCode();
        String content = "验证码为 " + code + " ，三分钟有效。请妥善保管！\n如非本人操作，请及时修改密码！";
        // 邮件内容
        emailDTO.setEmail(email);
        emailDTO.setTitle("东方既白博客系统——密码找回服务");
        emailDTO.setContent(content);
        emailService.sendMsg(emailDTO);
        // 往Redis中存储一个键值对
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(emailDTO.getEmail(), code, 180, TimeUnit.SECONDS);
        return Result.success();
    }

    @PostMapping("/user/retrieve")
    public Result retrievePassword(@RequestBody @Validated UserDTO userDTO){
        log.info("尝试找回密码");
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        if (operations.get(userDTO.getEmail()) == null){
            return Result.error("邮箱输入错误！");
        }
        if (!userDTO.getCode().equals(operations.get(userDTO.getEmail()))){
            return Result.error("验证码错误！");
        }
        // 查询用户
        User user = userService.findByUserEmail(userDTO.getEmail());
        userService.updatePwd(user.getId(), userDTO.getPassword());
        operations.getOperations().delete(user.getEmail());
        return Result.success();
    }

    @PostMapping("/user/loginByPassword")
    public Result<String> loginByPassword(@RequestParam @Email String email, String password, String code){
        // 根据邮箱查询User
        User user = userService.findByUserEmail(email);
        // redis
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        // 判断是否查询到
        if (user != null){
            if (code == null || !code.equals(operations.get("imageCode")))
                return Result.error("验证码错误！");
            if(Md5Util.checkPassword(password, user.getPassword())){
                // 登录成功
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", user.getId());
                claims.put("username", user.getUsername());
                String token = JwtUtil.genToken(claims);
                operations.getOperations().delete("imageCode");
                // 登录成功后，给浏览器响应令牌的同时，把该令牌存储到redis中
                operations.set(token, token, 1, TimeUnit.DAYS);
                return Result.success(token);
            }
            return Result.error("密码错误！");
        } else {
            return Result.error("用户未注册！");
        }
    }

    @PostMapping("/loginCode")
    public Result sendLoginCode(@RequestParam @Email String email){
        if(email == null || email.equals("")){
            return Result.error("请输入邮箱！");
        }
        EmailDTO emailDTO = new EmailDTO();
        // 设置验证码
        String code =  RandomUtil.getLoginCode();
        String content = "登录码为 " + code + " ，一分钟有效。\n请妥善保管，不要告知他人！\n如非本人操作，请及时修改密码！";
        // 邮件内容
        emailDTO.setEmail(email);
        emailDTO.setTitle("东方既白博客系统——登录服务");
        emailDTO.setContent(content);
        emailService.sendMsg(emailDTO);
        // 往Redis中存储一个键值对
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(emailDTO.getEmail(), code, 60, TimeUnit.SECONDS);
        return Result.success();
    }

    @PostMapping("/user/loginByCode")
    public Result<String> loginByCode(@RequestParam @Email String email, String code){
        // 根据邮箱查询User
        User user = userService.findByUserEmail(email);
        // 判断是否查询到
        if (user != null){
            // 往Redis中取邮箱对应的密码
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String sendCode = operations.get(email);
            if (sendCode == null)
                return Result.error("验证码错误！");
            if(sendCode.equals(code)){
                // 登录成功
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", user.getId());
                claims.put("username", user.getUsername());
                String token = JwtUtil.genToken(claims);
                // 登录成功后，给浏览器响应令牌的同时，把该令牌存储到redis中
                operations.set(token, token, 1, TimeUnit.DAYS);
//                System.out.println("存储token");
                return Result.success(token);
            }
            return Result.error("登录失败！");
        } else {
            return Result.error("用户未注册！");
        }
    }

    /**
     * 获取当前用户信息。
     * @return
     */
    @GetMapping("/user/info")
    public Result<User> getUserInfo(){
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer)map.get("id");
        User user = userService.findByUserId(id);
        return Result.success(user);
    }

    // 改用户名
    @PatchMapping("/user/updateName")
    public Result updateUsername(@RequestParam String name){
        userService.updateUsername(name);
        return Result.success();
    }

    // 改头像
    @PatchMapping("/user/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    // 修改密码
    @PatchMapping("/user/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params,@RequestHeader("Authorization") String token){
        // 1.校验参数
        String oldPwd = params.get("oldPwd");
        String newPwd = params.get("newPwd");
        String rePwd = params.get("rePwd");
        // 缺少参数
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数！");
        }
        // 原密码不正确
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer)map.get("id");
        User user = userService.findByUserId(id);
        if (!user.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码错误！");
        }
        // 两次输入不一样
        if (!newPwd.equals(rePwd)){
            return Result.error("两次密码不一致！");
        }
        // 2.修改密码
        userService.updatePwd(id,newPwd);
        // 删除redis中对应的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }

}
