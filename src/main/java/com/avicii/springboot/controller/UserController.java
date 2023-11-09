package com.avicii.springboot.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.avicii.springboot.controller.dto.UserDTO;
import com.avicii.springboot.entity.User;
import com.avicii.springboot.mapper.UserMapper;
import com.avicii.springboot.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController  //RestController注解一般绑定GetMapping注解
@RequestMapping("/user") //RequestMapping注解统一给接口加前缀
public class UserController {

//    @Autowired //注入其他类的注解
//    private UserMapper userMapper;

    @Autowired
    private UserService userService;


//    @PostMapping// RequestBody可把前端传的json转成java对象
//    public boolean login(@RequestBody UserDTO userDTO){
//        return userService.login(userDTO);
//    }


    // 新增和修改
    @PostMapping//插入接口
    public boolean save(@RequestBody User user){
        //新增或者更新
        return userService.saveUser(user);
    }

    // 查询所有数据
    @GetMapping
    public List<User> findAll(){

        // 自己写的findAll()方法，用userMapper实现的
//        List<User> all = userMapper.findAll();
//        return all;

        return userService.list();
    }

    @DeleteMapping("/{id}")  //PathVariable参数表示url的参数 /{}id与 Integer id必须对应
    public boolean delete(@PathVariable Integer id){
//        return userMapper.deleteById(id);

        return userService.removeById(id);
    }

    @PostMapping("/del/batch")  //PathVariable参数表示url的参数 /{}id与 Integer id必须对应
    public boolean deleteBatch(@RequestBody List<Integer> ids){
//        return userMapper.deleteById(id);

        return userService.removeByIds(ids);
    }


    // 分页查询接口
    // 接口路径: /user/page
    // @RequestParam接受 ?pageNum=1$pageSize=10
    // limit第一个参数 = （pageNum - 1） * pageSize  第二个参数 = pageSize
//    @GetMapping("/page")
//    public Map<String, Object> findPage(@RequestParam Integer pageNum,
//                                        @RequestParam Integer pageSize,
//                                        @RequestParam String username){
//        pageNum = (pageNum - 1) * pageSize;
//        username = "%" + username + "%";
//        List<User> data = userMapper.selectPage(pageNum, pageSize, username);
//        int total = userMapper.selectTotal(username); //查询总条数
//        Map<String, Object> res = new HashMap<>();
//        res.put("data", data);
//        res.put("total", total);
//
//        return res;
//
//    }

    // 分页查询 - mybatis-plus方法
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam (defaultValue = "")String username,
                                @RequestParam (defaultValue = "")String email,
                                @RequestParam (defaultValue = "")String address){
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username)){
            queryWrapper.like("username", username);
        }
        if (!"".equals(email)){
            queryWrapper.like("email", email);
        }
        if (!"".equals(address)){
            queryWrapper.like("address", address);
        }
        queryWrapper.orderByDesc("id");
        return userService.page(page, queryWrapper);

    }

    // 导出接口
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有数据
        List<User> list = userService.list();

        ExcelWriter writer = ExcelUtil.getWriter(true);
//        //自定义标题别名
//        writer.addHeaderAlias("username", "用户名");
//        writer.addHeaderAlias("password", "密码");
//        writer.addHeaderAlias("nickname", "昵称");
//        writer.addHeaderAlias("email", "邮箱");
//        writer.addHeaderAlias("phone", "电话");
//        writer.addHeaderAlias("address", "地址");
//        writer.addHeaderAlias("createTime", "创建时间");
//        writer.addHeaderAlias("avatarUrl", "头像");

        // 一次性写出list内的对象到excel（writer对象里去），使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        // writer对象给输出流，输出流再返回给浏览器
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }


    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<User> list = reader.readAll(User.class);

//        // 方式2：忽略表头的中文，直接读取表的内容
//        List<List<Object>> list = reader.read(1);
//        List<User> users = CollUtil.newArrayList();
//        for (List<Object> row : list) {
//            User user = new User();
//            user.setUsername(row.get(0).toString());
//            user.setPassword(row.get(1).toString());
//            user.setNickname(row.get(2).toString());
//            user.setEmail(row.get(3).toString());
//            user.setPhone(row.get(4).toString());
//            user.setAddress(row.get(5).toString());
//            user.setAvatar(row.get(6).toString());
//            users.add(user);
//        }

        userService.saveBatch(list);
        return true;
    }

}
