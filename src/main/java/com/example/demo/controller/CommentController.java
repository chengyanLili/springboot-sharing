package com.example.demo.controller;

import com.example.demo.entity.Comment;
import com.example.demo.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired(required=false)
    private CommentMapper commentMapper;

    @PostMapping
    public Integer save(@RequestParam String comment1, @RequestParam Integer userId, @RequestParam Integer sourId) {
        //新增
        Comment comment = new Comment();
        comment.setComment1(comment1);
        comment.setUserId(userId);
        comment.setSourId(sourId);
        return commentMapper.insert(comment);
    }

//    @GetMapping
//    public Integer findTotal(@RequestParam Integer sourId) {
//        //统计评论总数
//        Integer total = commentMapper.selectTotal(sourId);
//        return total;
//    }
}
