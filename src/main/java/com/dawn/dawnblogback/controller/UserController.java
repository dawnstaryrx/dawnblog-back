package com.dawn.dawnblogback.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: UserController
 * Package: com.dawn.dawnblogback.controller
 * Description:
 *
 * @Author yrx
 * @Create 2024/1/31 21:15
 * @Version 1.0
 */
@RestController
public class UserController {

    @RequestMapping("/test")
    public String test(){
        return "Hello";
    }
}
