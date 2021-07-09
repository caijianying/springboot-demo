package com.example.flywaydemo.dao;

import com.example.flywaydemo.entity.Person;
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
