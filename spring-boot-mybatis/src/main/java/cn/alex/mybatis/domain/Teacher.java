package cn.alex.mybatis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WCY on 2021/8/3
 */
@Getter
@Setter
@ToString
public class Teacher implements Serializable { // 实现序列化接口是为了将mybatis二级缓存序列化存储
    private Integer id;

    private String name;

    private String course;

    private String address;

    private Date birth;
}
