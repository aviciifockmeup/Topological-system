package com.avicii.springboot.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@TableName(value = "sys_graphtest")
public class Graph_test {
    @TableId(type = IdType.AUTO)

    private Integer id;
    private String protocal;
    private String ip;

    @TableField(typeHandler = JacksonTypeHandler.class)  // 实体类字段为JSON类型时，必须使用标签进行判定以排除其为null的可能性，否则会报下面的找不到为null时的指定
    private JSONObject geo_info;
}
