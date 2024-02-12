package com.dawn.dawnblogback.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ClassName: Article
 * Package: com.dawn.dawnblogback.pojo
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/12 17:54
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Integer id;
    private Integer author;
    private String title;
    private String content;
    private Integer categoryId;
    private Integer weight;
    private Integer like;
    private Integer state;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
