package com.dawn.dawnblogback.pojo;

import jakarta.validation.constraints.Email;
import lombok.Data;

/**
 * ClassName: UserDTO
 * Package: com.dawn.dawnblogback.pojo
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/1 14:45
 * @Version 1.0
 */
@Data
public class UserDTO {
    @Email
    private String email;
    private String code;
    private String password;
}
