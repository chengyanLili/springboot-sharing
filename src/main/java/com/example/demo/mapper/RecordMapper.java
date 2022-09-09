package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RecordMapper extends BaseMapper<Record> {

    @Select("SELECT * from record where user_id = #{userId} and sour_id = #{sourId}")
    Record selectRecord(Integer userId, Integer sourId);

}
