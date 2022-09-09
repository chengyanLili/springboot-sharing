package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("record")
public class Record {
    @TableId(type = IdType.AUTO)
    private Integer recordId;
    private Integer sourId;
    private Integer userId;
    private Boolean like1;
    private Boolean favor;

}
