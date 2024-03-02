package com.dawn.dawnblogback.service.impl;

import com.dawn.dawnblogback.mapper.CommentMapper;
import com.dawn.dawnblogback.pojo.Comment;
import com.dawn.dawnblogback.pojo.dto.CommentDTO;
import com.dawn.dawnblogback.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: CommentServiceImpl
 * Package: com.dawn.dawnblogback.service.impl
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/24 20:40
 * @Version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public void setChildren(CommentDTO commentDTO) {
        List<CommentDTO> children = commentMapper.findByParentId(commentDTO.getId());
        if (!children.isEmpty()) {
            commentDTO.setChildren(children);
            children.forEach(this::setChildren);
        }
    }

    @Override
    public List<CommentDTO> getCommentByParentId(Integer id) {
        List<CommentDTO> rootComments = commentMapper.findByParentId(id);
        rootComments.forEach(this::setChildren);
        return rootComments;
    }

    @Override
    public void add(Comment comment) {
        commentMapper.add(comment);
    }

    @Autowired
    private CommentMapper commentMapper;
}
