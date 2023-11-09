package com.avicii.springboot.mapper;

import com.avicii.springboot.entity.IpEdge;
import com.avicii.springboot.entity.IpNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IpEdgeMapper extends Neo4jRepository<IpEdge, Long> {




    //  MATCH (a:IpNode{value:'10.5.5.2'})
    //  MATCH (b:IpNode{value:'5.6.1.1'})
    //  CREATE (a) - [r:IPRELATIONSHIP{relation:'Arelationship'}] -> (b)

    @Query("MATCH (a:IpNode{value:$from})" +
            "MATCH (b:IpNode{value:$to}) " +
            "CREATE (a) - [r:IPRELATIONSHIP{relation:$relation}] -> (b)")
    String relevance(String from, String relation, String to);


    @Query("MATCH ()-[r:IPRELATIONSHIP]->() RETURN r")
    List<String> selectAll();


}
