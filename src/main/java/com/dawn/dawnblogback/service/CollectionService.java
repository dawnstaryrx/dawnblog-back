package com.dawn.dawnblogback.service;

import com.dawn.dawnblogback.pojo.Collection;

import java.util.List;

public interface CollectionService {
    void add(Integer articleId, Integer userId);

    void delete(Integer articleId, Integer userId);

    List<Collection> getList(Integer userId);

    Integer isCollected(Integer articleId, Integer userId);

    void whenDeleteArticle(Integer articleId);
}
