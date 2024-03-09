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
import java.util.List;
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
    public void haveFan(Integer id) {
        userMapper.changeFan(id, 1);
    }

    @Override
    public void haveFollow(Integer id) {
        userMapper.changeFollow(id, 1);
    }

    @Override
    public void updateCoin(Integer userId, Integer reward) {
        userMapper.updateCoin(userId, reward);
    }

    @Override
    public void changeUserRole(Integer id, Integer role) {
        userMapper.changeUserRole(id, role);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public void removeFan(Integer id) {
        userMapper.changeFan(id, -1);
    }

    @Override
    public void removeFollow(Integer id) {
        userMapper.changeFollow(id, -1);
    }

    @Override
    public void updateUsername(String name, String introduction) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer)map.get("id");
        LocalDateTime time = LocalDateTime.now();
        userMapper.updateUsername(id, name, introduction,time);
    }

    @Override
    public User findByUserId(Integer id) {
        System.out.println("service" + id);
        User user = userMapper.findByUserId(id);
        System.out.println(user.toString());
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
