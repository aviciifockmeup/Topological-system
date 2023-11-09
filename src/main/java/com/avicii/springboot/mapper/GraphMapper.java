package com.avicii.springboot.mapper;
import com.avicii.springboot.entity.Graph_test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GraphMapper extends BaseMapper<Graph_test> {

    @Select("SELECT id, protocal, geo_info, (JSON_TYPE(geo_info)) FROM sys_graphtest")
    List<Graph_test> findAll();

}
