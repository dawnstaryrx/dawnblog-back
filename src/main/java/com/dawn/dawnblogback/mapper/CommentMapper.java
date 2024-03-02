package com.dawn.dawnblogback.mapper;

import com.dawn.dawnblogback.pojo.Comment;
import com.dawn.dawnblogback.pojo.dto.CommentDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: CommentMapper
 * Package: com.dawn.dawnblogback.mapper
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/24 20:40
 * @Version 1.0
 */
@Mapper
public interface CommentMapper {
    @Insert("insert into comment set parent_id = #{parentId}, content = #{content}," +
            "author_id = #{authorId}, author_name = #{authorName}, author_avatar = #{authorAvatar}," +
            "create_time = NOW()")
    void add(Comment comment);

    @Select("select * from comment where parent_id = #{id}")
    List<CommentDTO> findByParentId(Integer id);
}
