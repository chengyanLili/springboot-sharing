package com.example.demo.controller;

import com.example.demo.controller.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
//    登录接口
//    @PostMapping
//    public boolean login(@RequestBody UserDTO user) {
//        return
//    }

    @Autowired
    private UserService userService;

    // 新增更新数据
    @PostMapping
    public Integer save(@RequestBody User user){
        return userService.save(user);
    }

//        查询数据
    @GetMapping
    public List<User> index(){
        return userMapper.findAll();
    }

}
