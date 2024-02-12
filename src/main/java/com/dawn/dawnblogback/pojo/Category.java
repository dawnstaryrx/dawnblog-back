package com.dawn.dawnblogback.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ClassName: Category
 * Package: com.dawn.dawnblogback.pojo
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/12 17:51
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;

    private String categoryName;

    private Integer createUser;

    private LocalDateTime createTime;

    private Integer state;
}
