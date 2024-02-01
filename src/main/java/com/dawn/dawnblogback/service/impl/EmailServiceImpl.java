package com.dawn.dawnblogback.service.impl;

import com.dawn.dawnblogback.pojo.EmailDTO;
import com.dawn.dawnblogback.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * ClassName: EmailServiceImpl
 * Package: com.dawn.dawnblogback.service.impl
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/1 12:35
 * @Version 1.0
 */
@Service
public class EmailServiceImpl implements EmailService {
    //邮件发送人 application.yml中的username
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMsg(EmailDTO emailDTO) {
        //创建一个对象
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        //开始发送
        mailMessage.setFrom(from);
        mailMessage.setTo(emailDTO.getEmail());
        mailMessage.setSubject(emailDTO.getTitle());
        mailMessage.setText(emailDTO.getContent());

        //真正的发送邮件操作，从from到to
        mailSender.send(mailMessage);
    }
}
