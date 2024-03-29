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
    public Result<Integer> signIn(){
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
        Integer num = signInService.getReward();
        return Result.success(num);
    }

    // 签到结果
    @GetMapping("/signin")
    public Result<List<SignInDTO>> getSignInList(){
        List<SignInDTO> list = new ArrayList<>(7);
        // 查询有没有用户记录
        SignIn signIn = signInService.isSignedIn();
        if (signIn == null){
            // 没有签过到
            for(int i = 1; i < 8;i++)
                list.add(new SignInDTO(i, 0));
        }else {
            // 签过到
            Integer continueDays = signIn.getContinueDays();
            // 前6天 flag固定
            if (continueDays <= 6){
                for (int i = 1; i < 8; i++){
                    if (i <= continueDays){
                        list.add(new SignInDTO(i, 1));
                    }else {
                        list.add(new SignInDTO(i, 0));
                    }
                }
            } else {
                // 6天后签到天数随日期增加
                for (int i = 5; i > -2; i--){
                    if (i > -1){
                        list.add(new SignInDTO(continueDays - i, 1));
                    } else {
                        list.add(new SignInDTO(continueDays - i, 0));
                    }
                }
            }
        }
        return Result.success(list);
    }

    // 当前用户签到数据
    @GetMapping("/signin/continue")
    public Integer getContinueDays(){
        return signInService.getContinueDays();
    }


    // 今天有没有签到
    @GetMapping("/signin/today")
    public Result<Integer> isTodaySignIn(){
        SignIn signIn = signInService.isSignedIn();
        // 如果没有，新增
        if (signIn == null){
            return Result.success(0);
        }else {// 如果有，修改
            // 判断今天是否签到，最后签到日期与当前日期是否超过一天
            LocalDate updateTime = signIn.getUpdateTime().toLocalDate();
            LocalDate currTime = LocalDate.now();
            long daysDiff = ChronoUnit.DAYS.between(updateTime, currTime);
            if (daysDiff <= 0) {
                // 今天已经签到过
                return Result.success(1);
            }
            return Result.success(0);
        }
    }
}
