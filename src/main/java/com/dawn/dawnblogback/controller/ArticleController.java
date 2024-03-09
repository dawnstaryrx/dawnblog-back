package com.dawn.dawnblogback.controller;

import com.dawn.dawnblogback.pojo.Article;
import com.dawn.dawnblogback.pojo.PageBean;
import com.dawn.dawnblogback.pojo.Result;
import com.dawn.dawnblogback.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: ArticleController
 * Package: com.dawn.dawnblogback.controller
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/3 20:12
 * @Version 1.0
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // 新增文章
    @PostMapping("/article")
    public Result addArticle(@RequestBody Article article){
        articleService.addArticle(article);
        return Result.success();
    }

    // 更新文章
    @PutMapping("/article")
    public Result updateArticle(@RequestBody Article article){
        articleService.updateArticle(article);
        return Result.success();
    }

    // 获取文章详情
    @GetMapping("/article/{id}")
    public Result<Article> getArticle(@PathVariable("id") Integer id){
        return Result.success(articleService.getArticle(id));
    }

    // 删除文章
    @DeleteMapping("/article")
    public Result deleteArticle(@RequestParam Integer id){
        articleService.deleteArticle(id);
        return Result.success();
    }

    // 获取文章列表
    @GetMapping("/article")
    public Result<PageBean<Article>> list(Integer pageNum, Integer pageSize,
                                          @RequestParam(required = false) String categoryId,
                                          @RequestParam(required = false) String state){
        PageBean<Article> pageBean = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pageBean);
    }

    @GetMapping("/search")
    public Result<PageBean<Article>> search(Integer pageNum, Integer pageSize, String searchInfo, String state){
        PageBean<Article> pageBean = articleService.search(pageNum, pageSize,searchInfo, state);
        return Result.success(pageBean);
    }

    // 获取文章列表
    @GetMapping("/article/my")
    public Result<PageBean<Article>> myList(Integer pageNum, Integer pageSize,
                                            @RequestParam(required = false) String state,
                                            Integer userId){
        PageBean<Article> pageBean = articleService.myList(pageNum, pageSize, state, userId);
        return Result.success(pageBean);
    }

}
