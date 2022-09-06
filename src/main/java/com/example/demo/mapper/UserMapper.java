package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * from user")
    List<User> findAll();

    @Insert("INSERT into user(username,password,identify) VALUES (#{username},#{password},#{identify})")
    int insert(User user);

    @Update("UPDATE user set username=#{username}")
    int update(User user);

}
