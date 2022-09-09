package com.example.demo.controller;

import com.example.demo.entity.Label;
import com.example.demo.mapper.LabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired(required=false)
    private LabelMapper labelMapper;

    @GetMapping
    public List<Label> findAll() {
        return labelMapper.findAll();
    }
}
