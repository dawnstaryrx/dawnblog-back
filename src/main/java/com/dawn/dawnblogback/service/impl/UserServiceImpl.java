package com.dawn.dawnblogback.service.impl;


import com.dawn.dawnblogback.mapper.UserMapper;
import com.dawn.dawnblogback.pojo.User;
import com.dawn.dawnblogback.pojo.dto.UserDTO;
import com.dawn.dawnblogback.service.UserService;
import com.dawn.dawnblogback.util.Md5Util;
import com.dawn.dawnblogback.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;


/**
 * ClassName: UserServiceImpl
 * Package: com.dawn.dawnblogback.service.impl
 * Description:
 *
 * @Author yrx
 * @Create 2024/1/31 21:36
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void updatePwd(Integer id, String newPwd) {
        newPwd = Md5Util.getMD5String(newPwd);
        userMapper.updatePwd(newPwd, id);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatarUrl(avatarUrl, id);
    }

    @Override
    public void updateUsername(String name) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer)map.get("id");
        LocalDateTime time = LocalDateTime.now();
        userMapper.updateUsername(id, name, time);
    }

    @Override
    public User findByUserId(Integer id) {
        User user = userMapper.findByUserId(id);
        return user;
    }

    @Override
    public User findByUserEmail(String email) {
        User user = userMapper.findByUserEmail(email);
        return user;
    }

    @Override
    public void register(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getEmail());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        userMapper.insertUser(user);
    }
}
