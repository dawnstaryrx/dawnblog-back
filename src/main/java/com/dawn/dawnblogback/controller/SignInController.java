package com.dawn.dawnblogback.controller;

import com.dawn.dawnblogback.pojo.Result;
import com.dawn.dawnblogback.pojo.SignIn;
import com.dawn.dawnblogback.pojo.dto.SignInDTO;
import com.dawn.dawnblogback.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SignInController {
    @Autowired
    private SignInService signInService;

    @PostMapping("/signin")
    public Result signIn(){
        // 查询有没有用户记录
        SignIn signIn = signInService.isSignedIn();
        // 如果没有，新增
        if (signIn == null){
            signInService.add();
        }else{// 如果有，修改
            // 判断今天是否签到，最后签到日期与当前日期是否超过一天
            LocalDate updateTime = signIn.getUpdateTime().toLocalDate();
            LocalDate currTime = LocalDate.now();
            long daysDiff = ChronoUnit.DAYS.between(updateTime, currTime);
            if(daysDiff <= 0){
                // 今天已经签到过
                return Result.error("重复签到");
            }
            if(daysDiff > 1){
                // 超过一天，断签，把连续签到的天数重置为 1
                signIn.setContinueDays(1);
            }else{
                // 没有超过一天，把连续签到的天数+1
                signIn.setContinueDays(signIn.getContinueDays() + 1);
            }
            signIn.setUpdateTime(LocalDateTime.now());
            signInService.update(signIn);
        }
        // 获取奖励
        signInService.getReward();
        return Result.success();
    }

    // TODO 签到结果
    @GetMapping("/signin")
    public Result<List<SignInDTO>> getSignInList(){
        List<SignInDTO> list = new ArrayList<>(7);
        // 查询有没有用户记录
        SignIn signIn = signInService.isSignedIn();
        if (signIn == null){
            // 没有签过到
            for(int i = 1; i < 8;i++)
                list.add(new SignInDTO(i, 0));
        }
        return Result.success(list);
    }
}
