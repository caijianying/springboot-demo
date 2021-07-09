package com.example.democommon.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author caijianying
 */
@Data
@TableName("PERSON")
public class Person implements Serializable{
    @TableId
    int ID;
    @TableField("NAME")
    String NAME;
}
