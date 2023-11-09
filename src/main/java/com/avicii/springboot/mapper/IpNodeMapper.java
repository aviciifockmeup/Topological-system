package com.avicii.springboot.mapper;


import com.avicii.springboot.entity.IpNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IpNodeMapper extends Neo4jRepository<IpNode, Long> {


    // CQL语句，在IpNode节点from和IpNode节点to之间创建一条类型为IPRELATIONSHIP名字为relation的边
    @Query("MATCH (a:IpNode{value:$from}), (b:IpNode{value:$to}) " +
            "CREATE (a) - [r:IPRELATIONSHIP{relation:$relation}] -> (b) " +
            "RETURN ID(r)")
    Integer createRelationship(String from, String relation, String to);
}
