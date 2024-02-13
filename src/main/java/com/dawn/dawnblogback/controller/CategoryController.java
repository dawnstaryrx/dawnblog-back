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

    // 新增类别
    @PostMapping("/category")
    public Result addCategory(@RequestParam String categoryName){
        if (categoryName == null){
            return Result.error("类名为空！");
        }
        // TODO 查找是否重复
        categoryService.addCategory(categoryName);
        return Result.success();
    }

    // 展示类别列表
    @GetMapping("/category")
    public Result<List<Category>> list(){
        List<Category> result = categoryService.list();
        return Result.success(result);
    }

    @GetMapping("/category/success")
    public Result<List<Category>> listSuccess(){
        List<Category> result = categoryService.listSuccess();
        return Result.success(result);
    }

    // 根据id查询类别
    @GetMapping("/category/detail")
    public Result<Category> detail(@RequestParam Integer id){
        Category c = categoryService.detail(id);
        return Result.success(c);
    }

    // 根据id删除类别
    @DeleteMapping("/category")
    public Result delete( @RequestParam Integer id){
        categoryService.delete(id);
        return Result.success();
    }

    //  根据id修改类别状态
    @PatchMapping("/category/examine")
    public Result examine( @RequestParam Integer id, Integer state){
        categoryService.examine(id , state);
        return Result.success();
    }

}
