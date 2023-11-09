package com.avicii.springboot.controller;


import com.avicii.springboot.config.PythonConfig;
import com.avicii.springboot.config.PythonConfig1;
import com.avicii.springboot.entity.IpData;
import com.avicii.springboot.entity.Person;
import com.avicii.springboot.mapper.PersonMapper;
import com.avicii.springboot.service.IpNodeService;
import com.avicii.springboot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    IpNodeService ipNodeService;

    @GetMapping("/{name}")
    public Person findByName(@PathVariable("name") String name){

        Person person = personService.findByName(name);
        String personName = person.getName();
        Long personBorn = person.getBorn();

        System.out.printf(personName);
        System.out.printf(personBorn.toString());

        return person;
    }

    @GetMapping("/all")
    public List<Person> findAll(){

//        List<Person> personList = personService.findAll();
//        for (Person person : personList){
//            System.out.printf(person.getName() + "\n");
//        }
//        System.out.printf("num of persons: " + personList.size());
        List<Person> personList = new ArrayList<>();
        personList = personService.findAll();

        // 遍历所有的人
        for (Person person : personList){
//            System.out.printf(person.getName() +  person.getFollows().toString() + "\n");
            // 初始化这个人follow的名单
            List<String> followedNames = new ArrayList<>();

            // 遍历这个人所有follow的人
            for (Person followPerson : person.getFollows()){
//                System.out.printf("This person followed:" + followPerson.getName());
                // 往follow名单中添加name
                followedNames.add(followPerson.getName());

            }

            person.setFollowNames(followedNames);
            System.out.printf("follow名单： " + followedNames );
            System.out.printf("\n");

        }


        return personList;
    }





    // 测试调用python脚本的接口
    @PostMapping("/getNode/{nodeNum}/{edgeNum}")
    public String getNode(@PathVariable Integer nodeNum,
                           @PathVariable Integer edgeNum) throws IOException, InterruptedException {

        String result = null;
        try {
            result = PythonConfig.getPyResult(nodeNum, edgeNum);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    @PostMapping("/getNodeDetect/{protocal}/{dIp}/{max_ttl}/{timeout}")
    public String getNodeDetect(@PathVariable String protocal,
                                @PathVariable String dIp,
                                @PathVariable Integer max_ttl,
                                @PathVariable Float timeout) throws IOException, InterruptedException {

        String result = null;
        try {
            result = PythonConfig1.getPyResult(protocal, dIp, max_ttl, timeout);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



    @PostMapping("/getNode1/{nodeNum}/{edgeNum}")
    public List<String> getNode1(@PathVariable Integer nodeNum,
                                 @PathVariable Integer edgeNum) throws IOException, InterruptedException {

        IpData nodeData = ipNodeService.getIpNode(nodeNum, edgeNum);
        List<String> nodeList = nodeData.getIpNodeValueList();
        for (String item : nodeList){
            System.out.printf(item);
        }

        return nodeList;

    }



    // 用于测试判断controller是否启动
    public PersonController(){
        System.out.printf("this!----------------------------------------");
    }




}
