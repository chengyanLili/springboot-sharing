package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper

public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * from user")
    List<User> findAll();

    @Insert("insert into user(username,password,label_id1,label_id2,label_id3) values (#{username},#{password}," +
            "#{labelId1},#{labelId2},#{labelId3})")
    void insert(String username, String password, Integer labelId1, Integer labelId2, Integer labelId3);

    /* Integer update(User user);*/

    @Delete("delete from user where user_id = #{userId}")
    Integer deleteByUserId(@Param("userId") Integer userId);

    @Select("select * from user where username like concat('%',#{username},'%') limit #{pageNum}, #{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize, String username);

    @Select("select count(*) from user where username like concat('%',#{username},'%')")
    Integer selectTotal(String username);

}
