package com.dawn.dawnblogback.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignIn {
    private Integer id;
    private Integer userId;
    private Integer continueDays;
    private LocalDateTime updateTime;
}
