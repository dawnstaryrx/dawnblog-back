package com.dawn.dawnblogback.service;

import com.dawn.dawnblogback.pojo.Comment;
import com.dawn.dawnblogback.pojo.dto.CommentDTO;

import java.util.List;

/**
 * ClassName: CommentService
 * Package: com.dawn.dawnblogback.service
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/24 20:39
 * @Version 1.0
 */
public interface CommentService {
    void add(Comment comment);

    List<CommentDTO> getCommentByParentId(Integer id);

    void setChildren(CommentDTO commentDTO);
}
