package com.dawn.dawnblogback.pojo.dto;

import lombok.Data;

/**
 * ClassName: EmailDTO
 * Package: com.dawn.dawnblogback.pojo
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/1 9:59
 * @Version 1.0
 */
@Data
public class EmailDTO {
    // 收件邮箱
    private String email;
    // 标题
    private String title;
    // 内容
    private String content;
}
