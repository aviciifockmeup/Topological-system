package com.avicii.springboot.service;


import com.avicii.springboot.entity.Person;
import com.avicii.springboot.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonMapper personMapper;

    public Person findByName(String name){
        return personMapper.findByName(name);
    }

    public List<Person> findAll(){
        return personMapper.findAll();
    }





}
