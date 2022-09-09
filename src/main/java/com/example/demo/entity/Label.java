package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("label")
public class Label {
    @TableId(type = IdType.AUTO)
    private Integer labelId;
    private String name;
}
