package com.avicii.springboot.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.avicii.springboot.config.PythonConfig;
import com.avicii.springboot.config.PythonConfig1;
import com.avicii.springboot.entity.IpData;
import com.avicii.springboot.entity.IpKeyNode;
import com.avicii.springboot.entity.IpNode;
import com.avicii.springboot.mapper.IpNodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IpNodeService {

    @Autowired
    IpNodeMapper ipNodeMapper;

    public List<IpNode> findAll(){
        return ipNodeMapper.findAll();
    }



    // 通过PythonConfig调用python脚本解析输出
    // 将脚本返回的node信息和list信息整理，封装进IpData对象中
    public IpData getIpNode(Integer nodeNum, Integer edgeNum)throws IOException, InterruptedException{
        String result = null;
        List<String> IpNodeValueList = new ArrayList<>();
        List<String> IpEdgeValueList = new ArrayList<>();
        IpData ipData = new IpData();
        try {
            result = PythonConfig.getPyResult(nodeNum, edgeNum);

//            System.out.printf(String.valueOf(result.length()));

//            JSONArray jsonArray = JSONArray.parseArray(result);
//
//            jsonArray.getJSONObject(1);

            JSONObject jsonObject = JSONObject.parseObject(result);

            String node_list = jsonObject.getString("node_list");

            String edge_list = jsonObject.getString("edge_list");

            JSONArray nodeJsonArray = JSONArray.parseArray(node_list);
            JSONArray edgeJsonArray = JSONArray.parseArray(edge_list);

            for (int i = 0; i < nodeJsonArray.size(); i++) {
                String item = nodeJsonArray.getString(i);
                IpNodeValueList.add(item);
            }

            for (int i = 0; i < edgeJsonArray.size(); i++) {
                String item = edgeJsonArray.getString(i);
                IpEdgeValueList.add(item);
            }

            ipData.setIpNodeValueList(IpNodeValueList);
            ipData.setIpEdgeValueList(IpEdgeValueList);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return ipData;

    }


    public IpData getIpNodeDetect(String protocal, String dIp, Integer max_ttl, Float timeout)throws IOException, InterruptedException{
        String result = null;
        List<String> IpNodeValueList = new ArrayList<>();
        List<String> IpEdgeValueList = new ArrayList<>();
        IpData ipData = new IpData();
        try {
            result = PythonConfig1.getPyResult(protocal, dIp, max_ttl, timeout);

//            System.out.printf(String.valueOf(result.length()));

//            JSONArray jsonArray = JSONArray.parseArray(result);
//
//            jsonArray.getJSONObject(1);

            JSONObject jsonObject = JSONObject.parseObject(result);

            String node_list = jsonObject.getString("node_list");

            String edge_list = jsonObject.getString("edge_list");

            JSONArray nodeJsonArray = JSONArray.parseArray(node_list);
            JSONArray edgeJsonArray = JSONArray.parseArray(edge_list);

            for (int i = 0; i < nodeJsonArray.size(); i++) {
                String item = nodeJsonArray.getString(i);
                IpNodeValueList.add(item);
            }

            for (int i = 0; i < edgeJsonArray.size(); i++) {
                String item = edgeJsonArray.getString(i);
                IpEdgeValueList.add(item);
            }

            ipData.setIpNodeValueList(IpNodeValueList);
            ipData.setIpEdgeValueList(IpEdgeValueList);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return ipData;

    }







}
