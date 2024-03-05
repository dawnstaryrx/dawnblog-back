package com.dawn.dawnblogback.controller;

import com.dawn.dawnblogback.pojo.Category;
import com.dawn.dawnblogback.pojo.Result;
import com.dawn.dawnblogback.service.CategoryService;
import com.dawn.dawnblogback.service.UserService;
import com.dawn.dawnblogback.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @Autowired
    private UserService userService;

    // 新增类别
    @PostMapping("/category")
    public Result addCategory(@RequestParam String categoryName){
        if (categoryName == null){
            return Result.error("类名为空！");
        }
        // 根据类名查找是否重复
        Category category = categoryService.findByCategoryName(categoryName);
        if(category!=null){
            return Result.error("类名重复！");
        }
        categoryService.addCategory(categoryName);
        return Result.success();
    }

    // 展示类别列表
    @GetMapping("/category")
    public Result<List<Category>> list(){
//        Map<String, Object> map = ThreadLocalUtil.get();
//        Integer userId = (Integer) map.get("id");
//        Integer role = userService.findByUserId(userId).getRole();
//        if (role != 2){
//            return Result.error("！！！请立刻离开！！！");
//        }
        List<Category> result = categoryService.list();
        return Result.success(result);
    }

    // 展示审批成功的类别
    @GetMapping("/category/success")
    public Result<List<Category>> listSuccess(){
        List<Category> result = categoryService.listSuccess();
        return Result.success(result);
    }

    // 展示审批中的类别
    @GetMapping("/category/wait")
    public Result<List<Category>> listWait(){
        List<Category> result = categoryService.listWait();
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");
        // 权限！
        // 根据id获取用户权限级别
        Integer role = userService.findByUserId(userId).getRole();
        if (role == 2){
            return Result.success(result);
        }else{
            return Result.error("！！！请立刻离开！！！");
        }

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
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");
        Integer role = userService.findByUserId(userId).getRole();
        if (role != 2){
            return Result.error("！！！请立刻离开！！！");
        }
        categoryService.delete(id);
        return Result.success();
    }

    //  根据id修改类别状态
    @PatchMapping("/category/examine")
    public Result examine( @RequestParam Integer id, Integer state){
        categoryService.examine(id , state);
        return Result.success();
    }

    //  更新分类
    @PatchMapping("/category/update")
    public Result update(@RequestParam Integer id, String categoryName, Integer state){
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");
        Integer role = userService.findByUserId(userId).getRole();
        if (role != 2){
            return Result.error("！！！请立刻离开！！！");
        }
        categoryService.update(id, categoryName, state);
        return Result.success();
    }

}
