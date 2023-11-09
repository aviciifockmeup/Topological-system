package com.avicii.springboot.entity;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Node
@Data
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Long born;

    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.OUTGOING)
    private List<Person> follows;


    // 这个属性是为了整合前端展示边的数据格式
    private List<String> followNames;


    // Empty constructor required as of Neo4j API 2.0.5
    public Person(){

    };


    public Person(String name, Long born){
        this.name = name;
        this.born = born;
    }
}
