package com.avicii.springboot.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
@Data
public class IpNode {

    @Id
    @GeneratedValue
    private Long id;

    private String value;

    public IpNode(){

    };
    @JsonIgnore   // 11-15在手动展示100个节点时出现这个问题
    @Relationship(type = "IPRELATIONSHIP", direction = Relationship.Direction.OUTGOING)
    private List<IpNode> ipConnectedNodes;

    private List<String> connectedIPs;


    public IpNode(String value){
        this.value = value;
    }



}
