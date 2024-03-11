package com.dawn.dawnblogback.mapper;

import com.dawn.dawnblogback.pojo.Article;
import com.dawn.dawnblogback.pojo.PageBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Update("update article set title = #{title}, category_id = #{categoryId}," +
            "update_time = NOW(), content = #{content}, state = #{state} where id = #{id}")
    void update(Article article);

    @Select("select * from article where id = #{id}")
    Article getArticle(Integer id);

    @Delete("delete from article where id = #{id}")
    void delete(Integer id);

    List<Article> list(String categoryId, String state);

    List<Article> myList(Integer userId, String state);

    @Select("select count(*) from article where author = #{id} and state != 0")
    Integer getArticleNumByUserId(Integer id);

    @Select("select * from article where (title like concat('%', #{searchInfo}, '%') or content like concat('%', #{searchInfo}, '%') or author like concat('%', #{searchInfo}, '%'))  and state = #{state}")
    List<Article> search(String searchInfo, String state);

}
