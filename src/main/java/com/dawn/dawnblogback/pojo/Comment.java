package com.dawn.dawnblogback.pojo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ClassName: Comment
 * Package: com.dawn.dawnblogback.pojo
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/24 20:36
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer id;
    private  Integer parentId;
    @NotBlank
    private String content;
    private Integer authorId;
    private String authorName;
    private String authorAvatar;
    private Integer likeNum;
    private LocalDateTime createTime;
}
