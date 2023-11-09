package com.avicii.springboot.controller;


import com.avicii.springboot.entity.Graph_test;
import com.avicii.springboot.mapper.GraphMapper;
import com.avicii.springboot.service.GraphService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.util.List;


@RestController
@RequestMapping("/graph")
public class GraphController {

    @Autowired
    private GraphService graphService;

    @Autowired
    private GraphMapper graphMapper;

    @PostMapping("/import")
    public String uploadJson() throws IOException {
//        String filePath = "D:\\Projects\\SystemA\\springboot\\files\\test2.json";
//        String jsonData = new String(Files.readAllBytes(Paths.get(filePath)));
//
//        Graph_test graphTest = JSON.parseObject(jsonData, Graph_test.class);
//
//        graphService.saveGraph(graphTest);

        File jsonFile = new File("D:\\Projects\\SystemA\\springboot\\files\\test2.json");
        String jsonContent = FileUtils.readFileToString(jsonFile,"UTF-8");
        JSONArray jsonArray = JSONArray.parseArray(jsonContent);

        for (Object obj : jsonArray) {

                JSONObject jsonObject = (JSONObject) obj;
                String protocal = jsonObject.getString("protocal");
                String ip = jsonObject.getString("ip");
                JSONObject geo_info = jsonObject.getJSONObject("geo_info");


                Graph_test graphTest = new Graph_test();
                graphTest.setProtocal(protocal);
                graphTest.setIp(ip);
                graphTest.setGeo_info(geo_info);


                graphService.saveGraph(graphTest);

                System.out.printf("protocal:" + protocal);
                System.out.printf("ip:" + ip);
                System.out.printf("geo_info:" + geo_info.toJSONString());
            }


        return "this";
    }

    @PostMapping//插入接口
    public boolean save(@RequestBody Graph_test graphTest){
        //新增或者更新
        return graphService.saveGraph(graphTest);
    }

    @GetMapping
    public List<Graph_test> findAll(){

        return graphMapper.findAll();
    }

    @DeleteMapping("/{id}")  //PathVariable参数表示url的参数 /{}id与 Integer id必须对应
    public boolean delete(@PathVariable Integer id){

        return graphService.removeById(id);
    }

    @PostMapping("/del/batch")  //PathVariable参数表示url的参数 /{}id与 Integer id必须对应
    public boolean deleteBatch(@RequestBody List<Integer> ids){

        return graphService.removeByIds(ids);
    }


    @GetMapping("/page")
    public IPage<Graph_test> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam (defaultValue = "")String protocal,
                                @RequestParam (defaultValue = "")String ip){
        IPage<Graph_test> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Graph_test> queryWrapper = new QueryWrapper<>();
        if (!"".equals(protocal)){
            queryWrapper.like("protocal", protocal);
        }
        if (!"".equals(ip)){
            queryWrapper.like("ip", ip);
        }

        queryWrapper.orderByDesc("id");
        return graphService.page(page, queryWrapper);

    }


}
