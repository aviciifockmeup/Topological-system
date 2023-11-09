package com.avicii.springboot.mapper;

import com.avicii.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

// @Mapper //把Mapper这个类注入到Sringboot容器
// 在Mybatisplus里有@MapperScan("com.avicii.springboot.mapper")注释，可不用@Mapper注释
public interface UserMapper extends BaseMapper<User> {

//    @Select("SELECT * FROM sys_user")
//    List<User> findAll();
//
//    @Insert("INSERT INTO sys_user(username, password, nickname, email, phone, address) VALUES(#{username}, #{password},#{nickname}, #{email}, #{phone}, #{address});")
//    int insert(User user);
//
//    @Update("update sys_user set username = #{username}, password = #{password}, nickname = #{nickname}, email = #{email}, " +
//            "phone = #{phone}, address = #{address} where id = #{id}")
//    // Update最好写成动态SQL，在resources里配置mapper/*.xml
//    int update(User user);
//
//    @Delete("delete from sys_user where id = #{id}")
//    int deleteById(@Param("id") Integer id);
//
//    @Select("select * from sys_user where username like #{username} limit #{pageNum}, #{pageSize}")
//    List<User> selectPage(Integer pageNum, Integer pageSize, String username);
//
//    @Select("select count(*) from sys_user where username like #{username}")
//    int selectTotal(String username);
}
