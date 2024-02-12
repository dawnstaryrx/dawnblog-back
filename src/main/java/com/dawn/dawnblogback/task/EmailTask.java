package com.dawn.dawnblogback.task;

import com.dawn.dawnblogback.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName: EmailTask
 * Package: com.dawn.dawnblogback.task
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/3 12:00
 * @Version 1.0
 */
@Component
@Slf4j
public class EmailTask {
    @Autowired
    private EmailService emailService;
//    @Scheduled(cron = "0 17 12 * * ?")
//    public void task(){
//        EmailDTO emailDTO = new EmailDTO();
//        emailDTO.setEmail("1650396516@qq.com");
//        emailDTO.setContent("hhhhhh");
//        emailDTO.setTitle("定时发送");
//        emailService.sendMsg(emailDTO);
//    }

//    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
//    public void fixedDelay() throws Exception {
//        log.info("fixedDelay run");
//        TimeUnit.SECONDS.sleep(3);
//    }
}
