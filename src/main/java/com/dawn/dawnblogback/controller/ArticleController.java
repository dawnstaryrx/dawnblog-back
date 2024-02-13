package com.dawn.dawnblogback.controller;

import com.dawn.dawnblogback.pojo.Article;
import com.dawn.dawnblogback.pojo.Result;
import com.dawn.dawnblogback.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    @PostMapping("/article")
    public Result addArticle(@RequestBody Article article){
        articleService.addArticle(article);
        return Result.success();
    }


}
