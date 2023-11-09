package com.avicii.springboot.service;


import com.avicii.springboot.entity.Files;
import com.avicii.springboot.entity.User;
import com.avicii.springboot.mapper.FileMapper;
import com.avicii.springboot.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FileService extends ServiceImpl<FileMapper, Files> {


}
