package com.example.dubboconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.democommon.entity.Person;
import com.example.dubboapi.service.IPersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author caijianying
 */
@RestController
@RequestMapping("api/consume")
public class ConsumeController {

    @Reference
    IPersonService personService;

    @GetMapping("list")
    public List<Person> list(){
        List<Person> list = personService.getList();
        return list;
    }
}
