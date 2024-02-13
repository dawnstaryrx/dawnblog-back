package com.dawn.dawnblogback.service.impl;

import com.dawn.dawnblogback.mapper.ArticleMapper;
import com.dawn.dawnblogback.pojo.Article;
import com.dawn.dawnblogback.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: ArticleServiceImpl
 * Package: com.dawn.dawnblogback.service.impl
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/13 10:42
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void addArticle(Article article) {
        articleMapper.add(article);
    }


}
