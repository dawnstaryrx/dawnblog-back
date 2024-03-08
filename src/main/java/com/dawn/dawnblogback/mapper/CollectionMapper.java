package com.dawn.dawnblogback.mapper;

import com.dawn.dawnblogback.pojo.Collection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectionMapper {
    @Insert("insert into collection(article_id, user_id, create_time) " +
            "values (#{articleId}, #{userId}, NOW())")
    void add(Integer articleId, Integer userId);

    @Delete("delete from collection where article_id = #{articleId} and user_id = #{userId}")
    void delete(Integer articleId, Integer userId);

    @Select("select * from collection where user_id = #{userId}")
    List<Collection> getList(Integer userId);

    @Select("select count(*) from collection where article_id = #{articleId} and user_id = #{userId}")
    Integer isCollected(Integer articleId, Integer userId);
}
