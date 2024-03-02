package com.dawn.dawnblogback.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: CommentDTO
 * Package: com.dawn.dawnblogback.pojo.dto
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/24 20:55
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Integer id;
    private  Integer parentId;
    @NotBlank
    private String content;
    private Integer authorId;
    private String authorName;
    private String authorAvatar;
    private LocalDateTime createTime;
    private Integer likeNum;
    private List<CommentDTO> children;
}
