package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired(required=false)
    private UserMapper userMapper;

    /*public int save(User user) {
        if(user.getUserId() == null) { //user没有id，表示新增
            return userMapper.insert(user);
        } else { //否则表示更新
            return userMapper.update(user);
        }
    }*/

}
