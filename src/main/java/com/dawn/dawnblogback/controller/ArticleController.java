package com.dawn.dawnblogback.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: ArticleController
 * Package: com.dawn.dawnblogback.controller
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/3 20:12
 * @Version 1.0
 */
@RestController
public class ArticleController {

    @GetMapping("/getInfo")
    public Map<String, String> getInfo(){
        Map<String , String> bot1 = new HashMap<>();
        bot1.put("name", "tiger");
        bot1.put("num", "2");
        return bot1;
    }
}
