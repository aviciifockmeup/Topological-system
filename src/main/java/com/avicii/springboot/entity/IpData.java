package com.avicii.springboot.entity;


import lombok.Data;
import java.util.List;

@Data
public class IpData {
    private List<String> IpNodeValueList;
    private List<String> IpEdgeValueList;
}
