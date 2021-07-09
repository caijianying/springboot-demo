package com.example.dubboprovider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.democommon.entity.Person;
import com.example.dubboapi.service.IPersonService;
import com.example.dubboprovider.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author caijianying
 */
@Service
public class PersonServiceImpl implements IPersonService {
    @Autowired
    PersonDao personDao;

    @Override
    public List<Person> getList() {
        return personDao.getList();
    }
}
