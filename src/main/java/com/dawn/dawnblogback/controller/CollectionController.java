package com.dawn.dawnblogback.controller;

import com.dawn.dawnblogback.pojo.Collection;
import com.dawn.dawnblogback.pojo.Result;
import com.dawn.dawnblogback.service.CollectionService;
import com.dawn.dawnblogback.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @PutMapping("/collection")
    public Result add(@RequestParam Integer articleId){
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        collectionService.add(articleId, userId);
        return Result.success();
    }
    @DeleteMapping("/collection")
    public Result delete(@RequestParam Integer articleId){
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        collectionService.delete(articleId, userId);
        return Result.success();
    }
    @GetMapping("/collection")
    public Result<List<Collection>> getCollectionList(){
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Collection> list = collectionService.getList(userId);
        return Result.success(list);
    }

    @PostMapping("/collection")
    public Result isCollected(@RequestParam Integer articleId){
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        Integer isCollected = collectionService.isCollected(articleId, userId);
        if (isCollected == 1){
            // 1已经收藏
            return Result.success(1);
        }else{
            return Result.success(0);
        }
    }
}
