package com.dawn.dawnblogback.service.impl;

import com.dawn.dawnblogback.mapper.ArticleMapper;
import com.dawn.dawnblogback.pojo.Article;
import com.dawn.dawnblogback.pojo.PageBean;
import com.dawn.dawnblogback.service.ArticleService;
import com.dawn.dawnblogback.service.CollectionService;
import com.dawn.dawnblogback.util.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    @Autowired
    private CollectionService collectionService;

    @Override
    public void addArticle(Article article) {
        articleMapper.add(article);
    }

    @Override
    public Integer getArticleNumByUserId(Integer id) {
        return articleMapper.getArticleNumByUserId(id);
    }

    @Override
    public PageBean<Article> myList(Integer pageNum, Integer pageSize, String state, Integer userId) {
        // 1. 创建PageBean对象
        PageBean<Article> pageBean = new PageBean<>();
        // 2. 开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);
        // 3. 调用mapper
        List<Article> articles = articleMapper.myList(userId, state);
        // page中提供了方法，可以获取PageHelper分页查询后，得到的总记录数和当前页数据
        Page<Article> p = (Page<Article>) articles;
        // 把数据填充到pageBean对象中
        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());
        return pageBean;
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String state) {
        // 1. 创建PageBean对象
        PageBean<Article> pageBean = new PageBean<>();
        // 2. 开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);
        // 3. 调用mapper
//        Map<String, Object> map = ThreadLocalUtil.get();
//        Integer userId = (Integer) map.get("id");
//        List<Article> articles = articleMapper.list(userId, categoryId, state);
        List<Article> articles = articleMapper.list(categoryId, state);
        // page中提供了方法，可以获取PageHelper分页查询后，得到的总记录数和当前页数据
        Page<Article> p = (Page<Article>) articles;
        // 把数据填充到pageBean对象中
        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());
        return pageBean;
    }

    @Override
    public PageBean<Article> search(Integer pageNum, Integer pageSize,String searchInfo, String state) {
        // 1. 创建PageBean对象
        PageBean<Article> pageBean = new PageBean<>();
        // 2. 开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);
        // 3. 调用mapper
        List<Article> articles = articleMapper.search(searchInfo, state);
        // page中提供了方法，可以获取PageHelper分页查询后，得到的总记录数和当前页数据
        Page<Article> p = (Page<Article>) articles;
        // 把数据填充到pageBean对象中
        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());
        return pageBean;
    }

    @Override
    public void deleteArticle(Integer id) {
        articleMapper.delete(id);
        collectionService.whenDeleteArticle(id);
    }

    @Override
    public Article getArticle(Integer id) {
        Article article = articleMapper.getArticle(id);
        return article;
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.update(article);
    }
}
