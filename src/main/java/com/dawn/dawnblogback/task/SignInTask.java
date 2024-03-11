package com.dawn.dawnblogback.task;


import com.dawn.dawnblogback.pojo.SignIn;
import com.dawn.dawnblogback.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class SignInTask {
    @Autowired
    private SignInService signInService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void task(){
        List<SignIn> list = signInService.list();
        for(SignIn item : list){
            LocalDate updateTime = item.getUpdateTime().toLocalDate();// 上次签到时间在 前天
            LocalDate currTime = LocalDate.now();                     // 今天
            long daysDiff = ChronoUnit.DAYS.between(updateTime, currTime);
            // 如果昨天没有签到
            if(daysDiff > 1){
                // 把连续签到的天数重置为 0
                item.setContinueDays(0);
            }
            signInService.update(item);
        }
    }
}
