package com.dawn.dawnblogback.service.impl;


import com.dawn.dawnblogback.mapper.UserMapper;
import com.dawn.dawnblogback.pojo.User;
import com.dawn.dawnblogback.pojo.UserDTO;
import com.dawn.dawnblogback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
