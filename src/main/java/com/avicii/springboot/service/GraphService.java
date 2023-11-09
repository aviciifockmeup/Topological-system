package com.avicii.springboot.service;

import com.avicii.springboot.entity.Graph_test;
import com.avicii.springboot.mapper.GraphMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class GraphService extends ServiceImpl<GraphMapper, Graph_test> {

    public boolean saveGraph(Graph_test graphTest) {

//        if (user.getId() == null){
//            return boolean save = save(user); // mybatis-plus提供的方法，表示插入数据
//        }else {
//            return updateById(user);
//        }
        return saveOrUpdate(graphTest);
    }

}
