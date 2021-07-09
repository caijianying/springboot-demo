package com.example.flywaydemo.controller;

import com.example.flywaydemo.dao.PersonDao;
import com.example.flywaydemo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author caijianying
 */
@RestController
@RequestMapping("api/person")
public class PersonController {

    @Autowired
    PersonDao personDao;

    @GetMapping("list")
    public List<Person> list(){
        List<Person> list = personDao.getList();
        return list;
    }
}
