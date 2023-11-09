package com.avicii.springboot.service;


import com.avicii.springboot.controller.dto.UserDTO;
import com.avicii.springboot.entity.User;
import com.avicii.springboot.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //把Service这个类注入到Springboot容器
public class UserService extends ServiceImpl<UserMapper, User> {
    public boolean saveUser(User user) {

//        if (user.getId() == null){
//            return boolean save = save(user); // mybatis-plus提供的方法，表示插入数据
//        }else {
//            return updateById(user);
//        }
        return saveOrUpdate(user);
    }




//    @Autowired
//    private UserMapper userMapper;

    // 这是自己写的save方法，但使用mybatis继承service有自己的save方法
//    public int save(User user){
//        if(user.getId() == null){  //user没有id表示是新增
//            return userMapper.insert(user);
//        } else { // 否则为更新
//            return userMapper.update(user);
//
//        }
//    }


}
