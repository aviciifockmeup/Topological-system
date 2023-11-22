package com.avicii.springboot.controller;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.json.serialize.JSONArraySerializer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.avicii.springboot.entity.*;
import com.avicii.springboot.mapper.IpEdgeMapper;
import com.avicii.springboot.mapper.IpKeyNodeMapper;
import com.avicii.springboot.mapper.IpNodeMapper;
import com.avicii.springboot.service.IpKeyNodeService;
import com.avicii.springboot.service.IpNodeService;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.coyote.Request;
import org.apache.coyote.http11.upgrade.UpgradeProcessorExternal;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/ip")
public class IpNodeController {

    @Autowired
    IpNodeService ipNodeService;

    @Autowired
    IpNodeMapper ipNodeMapper;

    @Autowired
    IpKeyNodeMapper ipKeyNodeMapper;

    @Autowired
    IpKeyNodeService ipKeyNodeService;


    // 测试***********************************获取python脚本创建的IP组的values接口
    @PostMapping("/getIpNode/{nodeNum}/{edgeNum}")
    public List<String> getNode1(@PathVariable Integer nodeNum,
                                 @PathVariable Integer edgeNum) throws IOException, InterruptedException {

        IpData nodeData = ipNodeService.getIpNode(nodeNum, edgeNum);
        List<String> nodeList = nodeData.getIpNodeValueList();
        for (String item : nodeList){
            System.out.printf(item);
        }

        return nodeList;

    }


    // 测试***********************************上传通过python脚本创建的ip组节点到neo4j接口
    @PostMapping("/import/{nodeNum}/{edgeNum}")
    public List<IpNode> imp(@PathVariable Integer nodeNum,
                            @PathVariable Integer edgeNum) throws IOException, InterruptedException {

        // 初始化ip节点组
        List<IpNode> IpNodes = new ArrayList<>();
        // 使用python脚本（封装在getIpNode方法里）获取ip节点组的values
        IpData nodeData = ipNodeService.getIpNode(nodeNum, edgeNum);

        List<String> IpNodeValues = nodeData.getIpNodeValueList();

        List<String> IpEdgeValues = nodeData.getIpEdgeValueList();


        // 为ip节点实体赋值value
        for (String IpNodeValue : IpNodeValues){
            IpNode ipNode = new IpNode();
            ipNode.setValue(IpNodeValue);
            IpNodes.add(ipNode);
//            ipNodeMapper.save(ipNode);

        }

        // 先将ip节点组实体存入neo4j
        ipNodeMapper.saveAll(IpNodes);

        // 解析ip边
        for (String item : IpEdgeValues){
            System.out.printf(item);

            String[] ipGroup = item.split(",");
            String startIP = ipGroup[0];
            String endIP = ipGroup[1];

            // 用自定义CQL在neo4j中创建边
            Integer i = null;
            i = ipNodeMapper.createRelationship(startIP, "CRelationship", endIP);

        }


        return IpNodes;
    }


    /**
     10-30 第一次与ZTH合代码 上传通过python脚本创建的ip组节点到neo4j接口

     */
    @PostMapping("/importIPS/{protocal}/{dIp}/{max_ttl}/{timeout}")
    public List<IpNode> impIPS(@PathVariable String protocal,
                               @PathVariable String dIp,
                               @PathVariable Integer max_ttl,
                               @PathVariable Float timeout) throws IOException, InterruptedException {

        // 初始化ip节点组
        List<IpNode> IpNodes = new ArrayList<>();
        // 使用python脚本（封装在getIpNode方法里）获取ip节点组的values
        IpData nodeData = ipNodeService.getIpNodeDetect(protocal, dIp, max_ttl, timeout);

        List<String> IpNodeValues = nodeData.getIpNodeValueList();

        List<String> IpEdgeValues = nodeData.getIpEdgeValueList();


        // 为ip节点实体赋值value
        for (String IpNodeValue : IpNodeValues){
            IpNode ipNode = new IpNode();
            ipNode.setValue(IpNodeValue);
            IpNodes.add(ipNode);
//            ipNodeMapper.save(ipNode);

        }

        // 先将ip节点组实体存入neo4j
        ipNodeMapper.saveAll(IpNodes);

        // 解析ip边
        for (String item : IpEdgeValues){
            System.out.printf(item);

            String[] ipGroup = item.split(",");
            String startIP = ipGroup[0];
            String endIP = ipGroup[1];

            // 用自定义CQL在neo4j中创建边
            Integer i = null;
            i = ipNodeMapper.createRelationship(startIP, "DRelationship", endIP);

        }


        return IpNodes;
    }



    @GetMapping("/create100Ips")
    public List<IpNode> create100Ips() throws IOException, InterruptedException {

        // 初始化ip节点组
        List<IpNode> IpNodes = new ArrayList<>();
        // 使用python脚本（封装在getIpNode方法里）获取ip节点组的values
        IpData nodeData = ipNodeService.get100IpNode();

        List<String> IpNodeValues = nodeData.getIpNodeValueList();

        List<String> IpEdgeValues = nodeData.getIpEdgeValueList();


        // 为ip节点实体赋值value
        for (String IpNodeValue : IpNodeValues){
            IpNode ipNode = new IpNode();
            ipNode.setValue(IpNodeValue);
            IpNodes.add(ipNode);
//            ipNodeMapper.save(ipNode);

        }

        // 先将ip节点组实体存入neo4j
        ipNodeMapper.saveAll(IpNodes);

        // 解析ip边
        for (String item : IpEdgeValues){
            System.out.printf(item);

            String[] ipGroup = item.split(",");
            String startIP = ipGroup[0];
            String endIP = ipGroup[1];

            // 用自定义CQL在neo4j中创建边
            Integer i = null;
            i = ipNodeMapper.createRelationship(startIP, "DRelationship", endIP);

        }


        return IpNodes;



    }




    // 查询所有的IpNode节点，并为每个IpNode.connectedIPs属性（String）赋值（通过getIpConnectedNodes()的方法直接在neo4j中找与节点连接的边）
    @GetMapping("/all")
    public List<IpNode> findAll(){


        List<IpNode> ipNodeList = ipNodeService.findAll();

        // 遍历IpNode
        for (IpNode ipNode : ipNodeList){

            // 初始化和这个ipNode连接的节点的value数组
            List<String> ipConnectedValues = new ArrayList<>();

            // 遍历这个ipNode所有连接的节点
            for (IpNode connectedIp : ipNode.getIpConnectedNodes()){
                // 往连接节点组中加入节点value
                ipConnectedValues.add(connectedIp.getValue());

            }

            ipNode.setConnectedIPs(ipConnectedValues);
            System.out.printf(ipNode.getValue() + "\t" +  "connect名单： " + ipConnectedValues );
            System.out.printf("\n");

        }


        return ipNodeList;
    }


    // 测试***********************************在接口中向另一台机器发送http请求并获得响应结果
    @GetMapping("/getBaidu")
    public RequestTest getBaidu(@RequestParam (defaultValue = "")String dip,
                           @RequestParam (defaultValue = "")String protocal,
                           @RequestParam (defaultValue = "")String max_ttl,
                           @RequestParam (defaultValue = "")String timeout) throws Exception{

        System.out.printf(dip);
        System.out.printf(protocal);
        System.out.printf(max_ttl);
        System.out.printf(timeout);

        RequestTest requestTest = new RequestTest();
        requestTest.setDip(dip);
        requestTest.setProtocal(protocal);
        requestTest.setMax_ttl(max_ttl);
        requestTest.setTimeout(timeout);
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        HttpGet httpGet = new HttpGet("http://118.24.129.105:9877/");
//        HttpResponse response = httpClient.execute(httpGet);
//        HttpEntity entity = response.getEntity();
//        String s = EntityUtils.toString(entity, "utf-8");
//
//        System.out.printf(s);


        return requestTest;


    }
    /**
     11-17 与ZTH合代码
     发起一个总探测任务，输出总任务id taskidallocated
     */
    @GetMapping("/startDetection")
    public String startDetection(@RequestParam (defaultValue = "")String target,
                                 @RequestParam (defaultValue = "")String protocols,
                                 @RequestParam Integer model,
                                 @RequestParam Integer maxttl,
                                 @RequestParam Float timeout) throws Exception{

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://118.24.129.105:9877/task/trace?target=" + target + "&protocols=" + protocols + "&model=" + model + "&maxttl=" + maxttl + "&timeout=" + timeout);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String taskIdAllocated = EntityUtils.toString(entity, "utf-8");


        return taskIdAllocated;
    }


    /**
     11-17 与ZTH合代码
     获取当前任务各个worker返回的结果（汇总后）
     前端应反复请求此接口（每2s）用于动态展示

     后续：再写一个探测完的接口，用于存储本次探测任务的结果数据
     */
    @GetMapping("/getDetectionResult")
    public IpData getDetectionResult() throws Exception{

        IpData ipData = new IpData();

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://118.24.129.105:9877/task/get/current");
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String detectionResult = EntityUtils.toString(entity, "utf-8");

        JSONObject jsonObject = JSON.parseObject(detectionResult);
        JSONObject graphObject = jsonObject.getJSONObject("graph");

//        System.out.printf("graphObject: " + graphObject);

        JSONArray nodesArray = graphObject.getJSONArray("nodes_list");
        JSONArray edgesArray= graphObject.getJSONArray("edges_list");

        List<String> nodesList = nodesArray.toJavaList(String.class);
        List<String> edgeslist = edgesArray.toJavaList(String.class);

        ipData.setIpNodeValueList(nodesList);
        ipData.setIpEdgeValueList(edgeslist);


        int Nlength = nodesList.size();
        int Elength = edgeslist.size();

        System.out.printf(String.valueOf(Nlength) + "AND" + String.valueOf(Elength) + "\n");



        return ipData;
    }






    // 测试***********************************延时显示拓扑更新过程
    @GetMapping("/detectProcess")
    public List<DetectProcess> detectProcess() throws Exception{

        String detectResult = "[{\"path\": 1,\"node_list\": [\"A\", \"B\", \"C\"],\"edge_list\": [\"A,B\",\"B,C\"],\"waitTime\": 1},{\"path\": 2,\"node_list\": [\"A\", \"B\", \"C\", \"D\"],\"edge_list\": [\"A,B\",\"B,C\",\"A,C\"],\"waitTime\": 2},{\"path\": 3,\"node_list\": [\"A\", \"B\", \"C\", \"D\",\"E\"],\"edge_list\": [\"A,B\",\"B,C\",\"A,C\",\"B,E\"],\"waitTime\": 1},{\"path\": 4,\"node_list\": [\"A\", \"B\", \"C\", \"D\",\"E\",\"F\",\"G\"],\"edge_list\": [\"A,B\",\"B,C\",\"A,C\",\"B,E\",\"A,F\",\"B,F\",\"C,F\"],\"waitTime\": 3},{\"path\": 4,\"node_list\": [\"A\", \"B\", \"C\", \"D\",\"E\",\"F\",\"G\",\"H\",\"I\"],\"edge_list\": [\"A,B\",\"B,C\",\"A,C\",\"B,E\",\"A,F\",\"B,F\",\"C,F\",\"D,F\",\"I,H\",\"A,H\"],\"waitTime\": 3}]";


//        HttpClient httpClient = HttpClientBuilder.create().build();
//        HttpGet httpGet = new HttpGet("http://118.24.129.105:9877/");
//        HttpResponse response = httpClient.execute(httpGet);
//        HttpEntity entity = response.getEntity();
//        String s = EntityUtils.toString(entity, "utf-8");


        JSONArray jsonArray = JSONArray.parseArray(detectResult);

        List<DetectProcess> detectProcessList = new ArrayList<>();
        for (Object object : jsonArray){
            if (object instanceof JSONObject){
                JSONObject jsonObject = (JSONObject) object;
                int pathId = jsonObject.getInteger("path");
                String nodes = jsonObject.getString("node_list");
                List<String> nodeList = JSON.parseArray(nodes, String.class);
                String edges = jsonObject.getString("edge_list");
                List<String> edgeList = JSON.parseArray(edges, String.class);
                Float waitTime = jsonObject.getFloat("waitTime");

                DetectProcess detectProcess = new DetectProcess();
                detectProcess.setPathId(pathId);
                detectProcess.setIpNodes(nodeList);
                detectProcess.setIpEdges(edgeList);
                detectProcess.setWaitTime(waitTime);

                detectProcessList.add(detectProcess);

            }
        }



        return detectProcessList;

    }




    /**
     11-9 第一次与ZT合代码，关键节点识别使用test.py脚本
     脚本输入：【mySQL抽象拓扑图】表中最新的一条数据，写入json文件，python脚本的输入入口是json文件的地址
     脚本输出：json对象（python中为字典），key为每个ip的value，值为每个ip节点的重要性（keyValue）属性

     结果包含（IpKeyNode组存入neo4j，根据输入创建的边（KIPRELATIONSHIP）存入neo4j）

     后续：统计了IpKeyNode组的keyValue后，存入【mySQL关键节点拓扑图】表，每次展示只展示最新的一条数据

     */
    @GetMapping("/KeyNodeIdentification")
    public List<IpKeyNode> getKeyNode(String jsonPath) throws IOException, InterruptedException {

        List<IpKeyNode> ipKeyNodeList = new ArrayList<>();

        List<String> IpEdgeList = new ArrayList<>();

        // jsonPath应该是从数据库最新的抽象拓扑图取出来，写进的json文件
        jsonPath = "D:\\Projects\\SystemA\\springboot\\springboot\\src\\main\\python\\input1.json";
        String keyNodeResult = null;
        ipKeyNodeList = ipKeyNodeService.getKeyNode(jsonPath);


        ipKeyNodeMapper.saveAll(ipKeyNodeList);

        // 通过输入的json文件获取边信息，在neo4j中创建边
        File jsonFile = new File(jsonPath);
        String jsonData = ipKeyNodeService.getStr(jsonFile);
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        String edge_list = jsonObject.getString("edge_list");

        JSONArray edgeJsonArray = JSONArray.parseArray(edge_list);

        for (int i = 0; i < edgeJsonArray.size(); i++) {
            String item = edgeJsonArray.getString(i);
            IpEdgeList.add(item);

        }

        for (String item : IpEdgeList){
            System.out.printf(item);

            String[] ipGroup = item.split(",");
            String startIP = ipGroup[0];
            String endIP = ipGroup[1];

            // 用自定义CQL在neo4j中创建边
            Integer i = null;
            i = ipKeyNodeMapper.createRelationship(startIP, "KRelationship", endIP);

        }

        return ipKeyNodeList;

    }


    /**
     查询所有的IpNode节点，并为每个IpNode.connectedIPs属性（String）赋值（通过getIpConnectedNodes()的方法直接在neo4j中找与节点连接的边）

     后续应该是：先从【mySQL关键节点拓扑图】表中取最新的一条数据，存入neo4j，然后ipKeyNodeList = ipKeyNodeService.findAll()进行展示
     取数据的前提要保证neo4j为空（加一个判断）

     */
    @GetMapping("/allKeyNode")
    public List<IpKeyNode> findAllKeyNode(){


        List<IpKeyNode> ipKeyNodeList = new ArrayList<>();
        ipKeyNodeList = ipKeyNodeService.findAll();

        // 遍历IpKeyNode
        for (IpKeyNode ipKeyNode : ipKeyNodeList){

            // 初始化和这个ipKeyNode连接的节点的value数组
            List<String> ipConnectedValues = new ArrayList<>();

            // 遍历这个ipNode所有连接的节点
            for (IpKeyNode connectedIp : ipKeyNode.getIpConnectedNodes()){
                // 往连接节点组中加入节点value
                ipConnectedValues.add(connectedIp.getValue());

            }

            ipKeyNode.setConnectedIPs(ipConnectedValues);
            System.out.printf(ipKeyNode.getValue() + "\t" +  "connect名单： " + ipConnectedValues );
            System.out.printf("\n");

        }

        return ipKeyNodeList;
    }






    // 测试***********************************查询所有的IpNode
    @GetMapping("/selectAll")
    public List<IpNode> findSpecific(){

        return ipNodeMapper.findAll();


    }


    // 测试***********************************用于为两个节点间添一条边
    @PostMapping("/createOneSpecificEdge")
    public Integer creatEdge(){

        List<String> strings = new ArrayList<>();
        return ipNodeMapper.createRelationship("8.0.9.2","BRelationship", "7.1.4.10");



    }




}
