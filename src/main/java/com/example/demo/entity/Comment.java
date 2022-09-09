package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("comment")
public class Comment {

    @TableId(type = IdType.AUTO)
    private Integer commentId;
    private Integer sourId;
    private Integer userId;
    private String comment1;
}
