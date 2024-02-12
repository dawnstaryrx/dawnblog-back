package com.dawn.dawnblogback.controller;

import com.dawn.dawnblogback.pojo.Category;
import com.dawn.dawnblogback.pojo.Result;
import com.dawn.dawnblogback.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: CategoryController
 * Package: com.dawn.dawnblogback.controller
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/12 18:06
 * @Version 1.0
 */
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public Result addCategory(@RequestParam String categoryName){
        if (categoryName == null){
            return Result.error("类名为空！");
        }
        // 查找是否重复，因为需要审核，默认不重复了，就不需要查找这个功能了
        categoryService.addCategory(categoryName);
        return Result.success();
    }

    @GetMapping("/category")
    public Result<List<Category>> list(){
        List<Category> result = categoryService.list();
        return Result.success(result);
    }

    @GetMapping("/category/detail")
    public Result<Category> detail(@RequestParam Integer id){
        Category c = categoryService.detail(id);
        return Result.success(c);
    }

    @DeleteMapping("/category")
    public Result delete( @RequestParam Integer id){
        categoryService.delete(id);
        return Result.success();
    }
}
