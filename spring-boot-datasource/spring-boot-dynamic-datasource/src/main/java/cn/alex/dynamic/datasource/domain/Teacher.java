package cn.alex.dynamic.datasource.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Teacher implements Cloneable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 课程
     */
    private String course;

    /**
     * 地址
     */
    private String address;

    /**
     * 生日
     */
    private Date birth;

    @Override
    public Teacher clone() throws CloneNotSupportedException {
        return (Teacher) super.clone();
    }
}

