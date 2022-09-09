package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {

    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String username;
    @JsonIgnore
    private String password;
    private Integer identify;
    private Integer labelId1;
    private Integer labelId2;
    private Integer labelId3;
}
