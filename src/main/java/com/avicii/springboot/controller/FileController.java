package com.avicii.springboot.controller;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.avicii.springboot.entity.Files;
import com.avicii.springboot.entity.User;
import com.avicii.springboot.mapper.FileMapper;
import com.avicii.springboot.service.FileService;
import com.avicii.springboot.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;


// 文件上传相关接口
@RestController
@RequestMapping("/file")
public class FileController {


    @Autowired
    private FileService fileService;

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource
    private FileMapper fileMapper;

    /**
     * 文件上传接口
     * @param file 前端传递过来的文件
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();
        // 先存储到磁盘
        File upLoadParentFile = new File(fileUploadPath);
        // 判断配置的文件目录是否存在
        if(!upLoadParentFile.exists()) {
            upLoadParentFile.mkdirs();
        }
        // 定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;
        File upLoadFile = new File(fileUploadPath + fileUUID);
        // 把获取到的文件存储到磁盘目录
        file.transferTo(upLoadFile);

        String url = "http://localhost:9090/file/" + fileUUID;

        // 存储数据库
        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size/1024);
        saveFile.setUrl(url);

        fileMapper.insert(saveFile);
        return url;

    }

    /**
     * 文件下载接口   http://localhost:9090/file/{fileUUID}
     * @param fileUUID
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }


    @PostMapping("/update")//更新接口
    public Integer save(@RequestBody Files files){
        //新增或者更新
        return fileMapper.updateById(files);
    }


    @DeleteMapping("/{id}")  //PathVariable参数表示url的参数 /{}id与 Integer id必须对应
    public Integer delete(@PathVariable Integer id){
//        return userMapper.deleteById(id);
        Files files = fileMapper.selectById(id);
        files.setIsDelete(true);
        return fileMapper.updateById(files);
    }

    @PostMapping("/del/batch")  //PathVariable参数表示url的参数 /{}id与 Integer id必须对应
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        // select * from sys_file where id in (id,id,id...)
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        List<Files> files = fileMapper.selectList(queryWrapper);
        for (Files file : files) {
            file.setIsDelete(true);
            fileMapper.updateById(file);
        }
        return true;
    }



    // 分页查询 - mybatis-plus方法
    @GetMapping("/page")
    public IPage<Files> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam (defaultValue = "")String name) {
        IPage<Files> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        // 查询未删除的记录
        queryWrapper.eq("is_delete", false);

        if (!"".equals(name)){
            queryWrapper.like("name", name);
        }
        queryWrapper.orderByDesc("id");

        return fileService.page(page, queryWrapper);
    }



}
