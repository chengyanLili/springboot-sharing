package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.config.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource

    @Autowired(required=false)
    private UserMapper userMapper;

//    @Autowired(required=false)
//    private UserService userService;


//    @PostMapping
//    public Integer save(@RequestBody User user) {
//        //新增或更新
//        return userService.save(user);
//    }

    @GetMapping
    public List<User> findAll(){
        return userMapper.findAll();
    }

    @PostMapping("/login")
    public Result<?> login(@RequestParam String username,@RequestParam String password,@RequestParam Integer identify) { //？表示任何一种类型
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username).eq(User::getPassword, password).eq(User::getIdentify, identify));
        /*System.out.print(res);*/
        if(res == null) {
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success(res);
    }
    @PostMapping("/manager")
    public Integer update(@RequestParam Integer userId) {
        User user = userMapper.selectById(userId);
        user.setIdentify(0);
        return userMapper.updateById(user);
    }
    @PostMapping("/register")
    public Result<?> register(@RequestParam String username,@RequestParam String password,
                              @RequestParam Integer labelId1, @RequestParam Integer labelId2, @RequestParam Integer labelId3) { //？表示任何一种类型
        System.out.print(username);
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        if(res != null) {
            return Result.error("-1","用户名重复");
        }
        if (password == null) {
            password = "123456";
        }
        userMapper.insert(username, password, labelId1, labelId2, labelId3);
        return Result.success();
    }

    @DeleteMapping("/{userId}")
    public Integer delete(@PathVariable Integer userId) {
        return userMapper.deleteByUserId(userId);
    }

    // 分页查询
    // 接口路径
    // @RequestParam 接受 ？pageNum=1&pageSize=10
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String username){

        pageNum = (pageNum - 1) * pageSize;
        List<User> data = userMapper.selectPage(pageNum, pageSize,username);
        Integer total = userMapper.selectTotal(username);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
}
