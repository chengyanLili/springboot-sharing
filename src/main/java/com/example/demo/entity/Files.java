package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.sql.Date;

@Data
@TableName("source")
public class Files {

    @TableId(type = IdType.AUTO)
    private Integer sourId;
    private Integer userId;
    private Integer state;
    private String cover;
    private String url;
    private String title;
    private String brief;
    private String md5;
    private Date submitTime;
    private Integer likeNum;
    private Integer favorNum;
    private Integer commentNum;
    private Integer clickNum;
    private Float score;
    private Integer labelId1;
    private Integer labelId2;
    private Integer labelId3;
    private String type;
    private Long size;
    private Integer counting;
    private Boolean isDelete;
    private Boolean enable;
}
