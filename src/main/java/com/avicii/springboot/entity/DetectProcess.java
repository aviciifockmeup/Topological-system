package com.avicii.springboot.entity;


import lombok.Data;

import java.util.List;

@Data
public class DetectProcess {


    private Integer pathId;
    private List<String> IpNodes;
    private List<String> IpEdges;

    private float waitTime;

}
