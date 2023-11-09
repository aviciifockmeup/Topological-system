package com.avicii.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Date;

//lombok帮助entity简化Set&Get方法的代码

@Data
@TableName(value = "sys_user")
@ToString
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    @JsonIgnore  //忽略某个字段，不展示给前端
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;

    private Date createTime;
    @TableField(value = "avatar_url") // 指定数据库的字段名称
    private String avatar;


}
