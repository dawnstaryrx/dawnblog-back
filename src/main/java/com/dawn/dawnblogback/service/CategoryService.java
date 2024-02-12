package com.dawn.dawnblogback.service;

import com.dawn.dawnblogback.pojo.Category;

import java.util.List;

/**
 * ClassName: CategoryService
 * Package: com.dawn.dawnblogback.service
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/12 18:07
 * @Version 1.0
 */
public interface CategoryService {

    void addCategory(String name);

    List<Category> list();

    Category detail(Integer id);

    void delete(Integer id);
}
