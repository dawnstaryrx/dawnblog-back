package com.dawn.dawnblogback.service.impl;

import com.dawn.dawnblogback.mapper.CollectionMapper;
import com.dawn.dawnblogback.pojo.Collection;
import com.dawn.dawnblogback.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public void add(Integer articleId, Integer userId) {
        collectionMapper.add(articleId, userId);
    }

    @Override
    public void delete(Integer articleId, Integer userId) {
        collectionMapper.delete(articleId, userId);
    }

    @Override
    public List<Collection> getList(Integer userId) {
        List<Collection> list = collectionMapper.getList(userId);
        return list;
    }

    @Override
    public Integer isCollected(Integer articleId, Integer userId) {
        return collectionMapper.isCollected(articleId, userId);
    }
}
