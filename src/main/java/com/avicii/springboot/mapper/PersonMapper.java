package com.avicii.springboot.mapper;

import com.avicii.springboot.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonMapper extends Neo4jRepository<Person, Long>{

    Person findByName(String name);


}
