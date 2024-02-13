package com.dawn.dawnblogback.service.impl;

import com.dawn.dawnblogback.mapper.CategoryMapper;
import com.dawn.dawnblogback.pojo.Category;
import com.dawn.dawnblogback.service.CategoryService;
import com.dawn.dawnblogback.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ClassName: CategoryServiceImpl
 * Package: com.dawn.dawnblogback.service.impl
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/12 18:07
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> listSuccess() {
        List<Category> result = categoryMapper.listSuccess();
        return result;
    }

    @Override
    public void examine(Integer id, Integer state) {
        categoryMapper.examine(id, state);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }

    @Override
    public Category detail(Integer id) {
        Category c = categoryMapper.detail(id);
        return c;
    }

    @Override
    public List<Category> list() {
        List<Category> result = categoryMapper.list();
        return result;
    }

    @Override
    public void addCategory(String name) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        categoryMapper.add(name, userId);
    }
}
