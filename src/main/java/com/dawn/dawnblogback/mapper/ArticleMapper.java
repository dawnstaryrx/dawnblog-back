package com.dawn.dawnblogback.mapper;

import com.dawn.dawnblogback.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: ArticleMapper
 * Package: com.dawn.dawnblogback.mapper
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/13 10:43
 * @Version 1.0
 */
@Mapper
public interface ArticleMapper {
    @Insert("insert into article(author, title, content, category_id, state, create_time, update_time)" +
            " values(#{author}, #{title}, #{content}, #{categoryId}, #{state}, NOW(), NOW())")
    void add(Article article);
}
