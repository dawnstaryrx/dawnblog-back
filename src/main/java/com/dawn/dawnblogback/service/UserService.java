package com.dawn.dawnblogback.service;

import com.dawn.dawnblogback.pojo.User;
import com.dawn.dawnblogback.pojo.dto.UserDTO;

import java.util.List;

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

    void haveFan(Integer id);

    void haveFollow(Integer id);

    void removeFan(Integer id);

    void removeFollow(Integer id);

    List<User> getUserList();

    void changeUserRole(Integer id, Integer role);

    void updateCoin(Integer userId, Integer reward);
}
