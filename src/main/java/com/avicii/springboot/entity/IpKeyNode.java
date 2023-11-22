package com.avicii.springboot.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Data
@Node
public class IpKeyNode {

    @Id
    @GeneratedValue
    private Long id;

    private String value;

    private Float KeyValue;

    public IpKeyNode(){

    };

    @JsonIgnore
    @Relationship(type = "IPRELATIONSHIP", direction = Relationship.Direction.OUTGOING)
    private List<IpKeyNode> ipConnectedNodes;

    private List<String> connectedIPs;


    public IpKeyNode(String value){
        this.value = value;
    }

}
