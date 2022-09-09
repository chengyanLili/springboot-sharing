package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

//    @Insert("insert into comment(sourId, userId, comment) values (#{sourId}, #{userId}, #{comment})")
//    Integer insert(String comment, Integer userId, Integer sourId);

//    @Select("select count(*) from comment where sour_id = #{sourId}")
//    Integer selectTotal(Integer sourId);
}