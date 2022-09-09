package com.example.demo.mapper;

import com.example.demo.entity.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LabelMapper {

    @Select("SELECT * from label")
    List<Label> findAll();
}

