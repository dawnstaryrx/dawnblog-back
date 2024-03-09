package com.dawn.dawnblogback.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDTO {
    // 签到的天数
    private Integer day;
    // 1已经签到， 0未签到
    private Integer flag;
}
