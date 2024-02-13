package com.dawn.dawnblogback.mapper;

import com.dawn.dawnblogback.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: CategoryMapper
 * Package: com.dawn.dawnblogback.mapper
 * Description:
 *
 * @Author yrx
 * @Create 2024/2/12 18:07
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper {
    @Insert("insert into category(category_name, create_user, create_time) values (#{name}, #{userId}, now())")
    void add(String name, Integer userId);

    @Select("select * from category")
    List<Category> list();

    @Select("select * from category where id = #{id}")
    Category detail(Integer id);

    @Delete("delete from category where id = #{id}")
    void delete(Integer id);

    @Update("update category set state = #{state} where id = #{id}")
    void examine(Integer id, Integer state);
    @Select("select * from category where state = 2")
    List<Category> listSuccess();
}
