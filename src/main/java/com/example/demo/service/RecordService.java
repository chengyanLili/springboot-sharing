package com.example.demo.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.entity.Record;
import com.example.demo.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RecordService {

    @Resource

    @Autowired(required=false)
    private RecordMapper recordMapper;

    public Integer save(Integer userId, Integer sourId, Integer type, Boolean judge) {
        Record res = recordMapper.selectOne(Wrappers.<Record>lambdaQuery().eq(Record::getUserId, userId).eq(Record::getSourId,sourId));
        if(res == null){
            Record record = new Record();
            record.setUserId(userId);
            record.setSourId(sourId);
            if (type == 1) {
                record.setLike1(judge);
            } else {
                record.setFavor(judge);
            }
            return recordMapper.insert(record);
        } else {
            if (type == 1) {
                res.setLike1(judge);
            } else {
                res.setFavor(judge);
            }
            return recordMapper.updateById(res);
        }
    }
}
