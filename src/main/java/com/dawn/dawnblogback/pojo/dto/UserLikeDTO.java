package com.dawn.dawnblogback.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLikeDTO {
    private Integer infoId;
    private Integer likeUserId;
    private Integer status;
    private LocalDateTime updateTime;
}
