package com.avicii.springboot.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.avicii.springboot.entity.Task;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {


    /**
     11-18 与ZTH合代码
     获取当前总任务和各个worker的状态
     前端应当一直请求此接口，和ip/getDetectionResult一样

     后续：再写一个探测完后的接口，用于存储探测完的状态信息
     */
    @GetMapping("/getTaskNodeInfo")
    public List<Task> getTaskNodeInfo() throws Exception{

        List<Task> taskList = new ArrayList<>();


        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://118.24.129.105:9877/status/get");
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String TaskNodeInfo = EntityUtils.toString(entity, "utf-8");

        JSONObject jsonObject = JSON.parseObject(TaskNodeInfo);
        JSONObject taskStatus = jsonObject.getJSONObject("taskstatus");

        Integer publishTasksCount = taskStatus.getInteger("PublishTasksCount");
        Integer pendingTasksCount = taskStatus.getInteger("PendingTasksCount");
        Integer submittedTasksCount = taskStatus.getInteger("submittedTasksCount");
        Integer processingTasksCount = taskStatus.getInteger("processingTasksCount");
        JSONObject currentTask = taskStatus.getJSONObject("CurrentTask");
        String taskId = currentTask.getString("taskid");
        String target = currentTask.getString("target");


        JSONArray workNodesStatus = jsonObject.getJSONArray("worknodesstatus");
        for (Object object : workNodesStatus){
            if (object instanceof JSONObject){
                JSONObject worker = (JSONObject) object;
                String nodeId = worker.getString("nodeid");
                String ipPort = worker.getString("ipport");
                Integer nodeType = worker.getInteger("nodetype");
                Integer nodeStatus = worker.getInteger("nodestatus");
                String nodeInfo = worker.getString("nodeinfo");
                String nodeTask = worker.getString("nodetask");

                Task task = new Task();
                task.setTaskId(taskId);
                task.setTarget(target);
                task.setPublishTasksCount(publishTasksCount);
                task.setPendingTasksCount(pendingTasksCount);
                task.setSubmittedTasksCount(submittedTasksCount);
                task.setProcessingTasksCount(processingTasksCount);
                task.setNodeId(nodeId);
                task.setIpPort(ipPort);
                task.setNodeType(nodeType);
                task.setNodeStatus(nodeStatus);
                task.setNodeInfo(nodeInfo);
                task.setNodeTask(nodeTask);

                taskList.add(task);

            }

        }

        return taskList;

    }


}
