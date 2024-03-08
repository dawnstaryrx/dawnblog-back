package com.dawn.dawnblogback.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collection {
    private Integer id;
    private Integer articleId;
    private Integer userId;
    private LocalDateTime createTime;
}
