package com.dawn.dawnblogback.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ClassName: User
 * Package: com.dawn.dawnblogback.pojo
 * Description:
 *
 * @Author yrx
 * @Create 2024/1/31 21:37
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;

    private String email;

    private String username;

    private String password;

    private Integer role;

    private String avatar;

    private Integer coin;

    private String introduction;

    private Integer fanNum;

    private Integer followNum;

    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
