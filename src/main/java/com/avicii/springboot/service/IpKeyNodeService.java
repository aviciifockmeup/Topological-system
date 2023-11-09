package com.avicii.springboot.service;


import com.alibaba.fastjson.JSONObject;
import com.avicii.springboot.config.PythonConfig1;
import com.avicii.springboot.entity.IpKeyNode;
import com.avicii.springboot.entity.IpNode;
import com.avicii.springboot.mapper.IpKeyNodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class IpKeyNodeService {

    @Autowired
    IpKeyNodeMapper ipKeyNodeMapper;

    public List<IpKeyNode> findAll(){
        return ipKeyNodeMapper.findAll();
    }


    public List<IpKeyNode> getKeyNode(String jsonPath) throws InterruptedException, IOException {
        String result = null;

        List<IpKeyNode> ipKeyNodeList = new ArrayList<>();

        try {
            result = PythonConfig1.KeyNodeIdentification(jsonPath);

            JSONObject jsonObject = JSONObject.parseObject(result);

            for (String key : jsonObject.keySet()){
                // 获得属性名
                System.out.printf("keyName:" + key);
                // 获得属性值
                System.out.printf("keyValue:" + jsonObject.getFloat(key));
                Float keyValue = jsonObject.getFloat(key);


                IpKeyNode ipKeyNode = new IpKeyNode();
                ipKeyNode.setValue(key);
                ipKeyNode.setKeyValue(keyValue);

                ipKeyNodeList.add(ipKeyNode);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return ipKeyNodeList;

    }


    public String getStr(File jsonFile){
        String jsonStr = "";
        try {
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }




}
