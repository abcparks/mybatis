package cn.alex.mybatis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * Created by WCY on 2021/4/2
 */
@Getter
@Setter
@ToString
@Alias("emp")
public class Employee {
    private Integer id;

    private String empName;

    private String email;

    private Integer gender;

    public Employee() {
    }

    public Employee(Integer id, String empName, String email, Integer gender) {
        this.id = id;
        this.empName = empName;
        this.email = email;
        this.gender = gender;
    }
}
