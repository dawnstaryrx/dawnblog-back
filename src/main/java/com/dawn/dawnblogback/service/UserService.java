package com.dawn.dawnblogback.service;

import com.dawn.dawnblogback.pojo.User;
import com.dawn.dawnblogback.pojo.dto.UserDTO;

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

    User findByUserId(Integer id);

    void updateUsername(String name, String introduction);

    void updateAvatar(String avatarUrl);

    void updatePwd(Integer id, String newPwd);
}
