package com.example.demo.controller;

import com.example.demo.entity.Record;
import com.example.demo.mapper.RecordMapper;
import com.example.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired(required=false)
    private RecordMapper recordMapper;

    @Autowired(required=false)
    private RecordService recordService;

    @PostMapping
    public Integer save(@RequestParam Integer userId, @RequestParam Integer sourId, @RequestParam Integer type, @RequestParam Boolean judge) {
        //新增或更新
        return recordService.save(userId, sourId, type, judge);
    }
    @GetMapping
    public Record SelectRecord(@RequestParam Integer userId, @RequestParam Integer sourId) {
        return recordMapper.selectRecord(userId,sourId);
    }
}
