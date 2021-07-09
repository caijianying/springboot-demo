package com.example.dubboprovider.dao;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.democommon.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author caijianying
 */
@Mapper
public interface PersonDao {

    @Select("select * from PERSON")
    List<Person> getList();
}
