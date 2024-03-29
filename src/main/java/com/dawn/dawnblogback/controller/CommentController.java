package com.dawn.dawnblogback.controller;

import com.dawn.dawnblogback.pojo.Comment;
import com.dawn.dawnblogback.pojo.Result;
import com.dawn.dawnblogback.pojo.dto.CommentDTO;
import com.dawn.dawnblogback.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: CommentController
 * Package: com.dawn.dawnblogback.controller
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/24 20:39
 * @Version 1.0
 */
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public Result add(@RequestBody @Validated Comment comment){
        commentService.add(comment);
        return Result.success();
    }

    @GetMapping("/comment/{id}")
    public Result<List<CommentDTO>> getCommentByParentId(@PathVariable("id") Integer id){
        List<CommentDTO> list = commentService.getCommentByParentId(id);
        return Result.success(list);
    }

    @GetMapping("/comment/likeNum")
    public Result getLikeNum(@RequestParam Integer infoId){
        Integer likeNum = commentService.getLikeNum(infoId);
        return Result.success(likeNum);
    }

    @PutMapping("/comment/like")
    public Result like(@RequestParam Integer infoId, Integer likeUserId){
        commentService.like(infoId, likeUserId);
        return Result.success();
    }

    @DeleteMapping("/comment/dislike")
    public Result dislike(@RequestParam Integer infoId, Integer likeUserId){
        commentService.dislike(infoId, likeUserId);
        return Result.success();
    }

    // 判断当前用户是否已经点赞,1是 0否
    @GetMapping("/comment/isLiked")
    public Result isLiked(@RequestParam Integer infoId){
        Integer isLiked = commentService.isLiked(infoId);
        return Result.success(isLiked);
    }
}
