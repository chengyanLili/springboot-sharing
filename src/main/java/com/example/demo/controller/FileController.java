package com.example.demo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.config.Result;
import com.example.demo.entity.Files;
import com.example.demo.entity.User;
import com.example.demo.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  文件上传相关接口
*/
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource
    private FileMapper fileMapper;

    @GetMapping("/search")
    public List<Files> findSearch(@RequestParam String searchText) {
        List<Files> filesList = fileMapper.findSearch(searchText);
        for(int i=0;i<filesList.size();i++) {
            Files b = filesList.get(i);
            if(b.getState()==2 || b.getState()==0){
                filesList.remove(b);
            }
        }
        return filesList;
    }

    @PostMapping("/calculate")
    public Result<?> calculate(@RequestBody User user){
        System.out.println(user);
        List<Files> filesList = fileMapper.selectList(null);
        //剔除List中state等于0或1
        for(int i=0;i<filesList.size();i++) {
            Files b = filesList.get(i);
            if(b.getState()==2 || b.getState()==0){
                filesList.remove(b);
            }
        }
        //System.out.println(filesList);
        for(int i=0;i<filesList.size();i++) {
            Files files = filesList.get(i);
            if( files.getLabelId1()==user.getLabelId1()|| files.getLabelId1()==user.getLabelId2()|| files.getLabelId1()==user.getLabelId3()) {
                files.setCounting(files.getCounting()+1);
            }
            if( files.getLabelId2()==user.getLabelId1()|| files.getLabelId2()==user.getLabelId2()|| files.getLabelId2()==user.getLabelId3()) {
                files.setCounting(files.getCounting()+1);
            }
            if( files.getLabelId3()==user.getLabelId1()|| files.getLabelId3()==user.getLabelId2()|| files.getLabelId3()==user.getLabelId3()) {
                files.setCounting(files.getCounting()+1);
            }
            double e = Math.E;//自然常数e的近似值
            Date nowTime = new Date(System.currentTimeMillis());
            //得分公式
            files.setScore((float) ((0.3*files.getCounting()+1)*(files.getCommentNum()+files.getLikeNum()+files.getFavorNum()+0.2* files.getClickNum()-Math.pow(e,0.1*(nowTime.getTime()-files.getSubmitTime().getTime())/1000/60/60/24))));
            filesList.set(i,files);
        }
        System.out.println(filesList);
        //将list按score从大到小排序
        filesList.sort(Comparator.comparing(Files::getScore).reversed());
        //取前几条数据推荐给用户
        if(filesList.size()>=10) {
            return Result.success(filesList.subList(0, 9));
        }
        else{
            return Result.success(filesList.subList(0, filesList.size()));
        }
    }


    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file, @RequestParam Integer userId, @RequestParam String title,@RequestParam String brief,@RequestParam Integer labelId1,@RequestParam Integer labelId2,@RequestParam Integer labelId3) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        //定义一个文件唯一标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);

        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()) {
            parentFile.mkdirs();
        }

        String url;
        // 获取文件的md5
        String md5 = SecureUtil.md5(file.getInputStream());
        // 从数据库查询是否存在相同的记录
        Files dbFiles = getFileByMd5(md5);
        if (dbFiles != null) { // 文件已存在
            url = dbFiles.getUrl();
        } else {
            // 上传文件到磁盘
            file.transferTo(uploadFile);
            // 数据库若不存在重复文件，则不删除刚才上传的文件
            url = "http://localhost:9090/file/" + fileUUID;
        }

        //存储数据库
        Files saveFile = new Files();
        saveFile.setTitle(title);
        saveFile.setType(type);
        saveFile.setSize(size/1024);
        saveFile.setUrl(url);
        saveFile.setUserId(userId);
        saveFile.setBrief(brief);
        saveFile.setMd5(md5);
        saveFile.setLabelId1(labelId1);
        saveFile.setLabelId2(labelId2);
        saveFile.setLabelId3(labelId3);
        fileMapper.insert(saveFile);
        return url;
    }

    @GetMapping
    public List<Files> findAll(){
        return fileMapper.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Files> findByUserId(@PathVariable Integer userId){
        return fileMapper.findByUserId(userId);
    }

    @GetMapping("/{fileUUID}")
    private void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        File uploadFile = new File(fileUploadPath + fileUUID);
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

//    @GetMapping
//    public List<Files> findAll(){
//        return fileMapper.findAll();
//    }
    @DeleteMapping("/{sourId}")
    public Integer delete(@PathVariable Integer sourId) {

        return fileMapper.deleteById(sourId);
    }

    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String title){
        System.out.println(pageNum);
        System.out.println(pageSize);

        pageNum = (pageNum - 1) * pageSize;
        List<Files> data = fileMapper.selectPage(pageNum, pageSize,title);
        Integer total = fileMapper.selectTotal(title);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    @GetMapping("/sourId")
    public Files getBySourId(@RequestParam Integer sourId){

        return fileMapper.selectById(sourId);
    }

    @PostMapping("/check")
//审核后状态变化
    public Integer updateState(@RequestParam Integer sourId, @RequestParam Integer checkRes, @RequestParam Date submitTime) {
        System.out.println(submitTime);
        Files files = fileMapper.selectById(sourId);
        //1通过  2不通过
        files.setState(checkRes);
        files.setSubmitTime(submitTime);
        return fileMapper.updateById(files);
    }

    @PostMapping("/clickNum")
    //增加点击量
    public Integer update(@RequestParam Integer sourId) {
        return fileMapper.updateClick(sourId);
    }


    /**
     * 通过文件的md5查询文件
     * @param md5
     * @return
     */
    private Files getFileByMd5(String md5) {
        // 查询文件的md5是否存在
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<Files> filesList = fileMapper.selectList(queryWrapper);
        return filesList.size() == 0 ? null : filesList.get(0);
    }
    
}
