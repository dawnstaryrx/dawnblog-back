package com.dawn.dawnblogback.service;

import com.dawn.dawnblogback.pojo.Result;
import com.dawn.dawnblogback.pojo.User;
import com.dawn.dawnblogback.pojo.UserDTO;

/**
 * ClassName: UserService
 * Package: com.dawn.dawnblogback.service
 * Description:
 *
 * @Author yrx
 * @Create 2024/1/31 21:36
 * @Version 1.0
 */
public interface UserService {


    void register(UserDTO userDTO);

    User findByUserEmail(String email);
}
