package com.avicii.springboot.mapper;


import com.avicii.springboot.entity.IpKeyNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IpKeyNodeMapper extends Neo4jRepository<IpKeyNode, Long> {


    // CQL语句，在IpNode节点from和IpNode节点to之间创建一条类型为IPRELATIONSHIP名字为relation的边
    @Query("MATCH (a:IpKeyNode{value:$from}), (b:IpKeyNode{value:$to}) " +
            "CREATE (a) - [r:IPRELATIONSHIP{relation:$relation}] -> (b) " +
            "RETURN ID(r)")
    Integer createRelationship(String from, String relation, String to);
}
