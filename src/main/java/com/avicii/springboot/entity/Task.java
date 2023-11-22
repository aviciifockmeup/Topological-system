package com.avicii.springboot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_task")
public class Task {

    @TableId(type = IdType.AUTO)
    private Integer id;

    // 总任务的属性
    private String taskId;

    private String target;
    private Integer publishTasksCount;
    private Integer pendingTasksCount;
    private Integer submittedTasksCount;
    private Integer processingTasksCount;

    // 任务各个worker的属性
    private String nodeId;

    private String ipPort;

    private Integer nodeType;

    private Integer nodeStatus;

    private String nodeInfo;

    private String nodeTask;





}
