package com.dawn.dawnblogback.controller;

import com.dawn.dawnblogback.pojo.Result;
import com.dawn.dawnblogback.service.FileService;
import com.dawn.dawnblogback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file){
        String url = fileService.upload(file);
        return Result.success(url);
    }

    @PostMapping("/upload/avatar")
    public Result uploadAvatar(MultipartFile file){
        String url = fileService.upload(file);
        userService.updateAvatar(url);
        return Result.success(url);
    }
}
