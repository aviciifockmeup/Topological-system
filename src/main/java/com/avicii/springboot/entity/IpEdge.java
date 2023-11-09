package com.avicii.springboot.entity;


import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;

@Data
@RelationshipProperties
public class IpEdge implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private final String name;

    @TargetNode // 相当于@StartNode
    private final IpNode ipNode;






}
