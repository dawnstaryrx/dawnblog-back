package com.dawn.dawnblogback.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLike {
    private Integer id;
    private Integer infoId;
    private Integer likeUserId;
    private LocalDateTime createTime;
}
