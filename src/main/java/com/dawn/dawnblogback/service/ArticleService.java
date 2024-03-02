package com.dawn.dawnblogback.service;

import com.dawn.dawnblogback.pojo.Article;
import com.dawn.dawnblogback.pojo.PageBean;

/**
 * ClassName: ArticleService
 * Package: com.dawn.dawnblogback.service
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/13 10:42
 * @Version 1.0
 */
public interface ArticleService {
    void addArticle(Article article);

    void updateArticle(Article article);

    Article getArticle(Integer id);

    void deleteArticle(Integer id);

    PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String state);

    PageBean<Article> myList(Integer pageNum, Integer pageSize);
}
