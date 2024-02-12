package com.dawn.dawnblogback.service;

import com.dawn.dawnblogback.pojo.dto.EmailDTO;

/**
 * ClassName: EmailService
 * Package: com.dawn.dawnblogback.service
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/1 12:34
 * @Version 1.0
 */
public interface EmailService {

    public void sendMsg(EmailDTO emailDTO);
}
