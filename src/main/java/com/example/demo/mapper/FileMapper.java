package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Files;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface FileMapper extends BaseMapper<Files> {

    @Select("select * from source where title like concat('%',#{title},'%') limit #{pageNum}, #{pageSize}")
    List<Files> selectPage(Integer pageNum, Integer pageSize, String title);

    @Select("select count(*) from source where title like concat('%',#{title},'%')")
    Integer selectTotal(String title);

    @Select("SELECT * from source")
    List<Files> findAll();

    @Select("SELECT * from source where user_id = #{userId}")
    List<Files> findByUserId(Integer userId);

    @Update("UPDATE source SET click_num=click_num+1  WHERE sour_id = #{sourId}")
    Integer updateClick(Integer sourId);

    @Select("SELECT * FROM source where title like concat('%',#{searchText},'%') or brief like concat('%',#{searchText},'%')")
    List<Files> findSearch(String searchText);
}
