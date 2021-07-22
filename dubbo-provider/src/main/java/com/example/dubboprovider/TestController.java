package com.example.dubboprovider;

import com.alibaba.fastjson.JSON;
import com.example.democommon.entity.Person;
import com.example.dubboapi.service.IPersonService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author caijianying
 */
@Slf4j
@RestController
@RequestMapping("api/test")
public class TestController {

    @Autowired
    IPersonService personService;

    @GetMapping
    public String test(){
        List<Person> list = personService.getList();
        log.info(JSON.toJSONString(list));
        return "success";
    }
}
