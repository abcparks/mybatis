package cn.alex.transactional;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by WCY on 2022/5/16
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.alex.transactional.mapper")
public class TransactionalApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionalApplication.class, args);
    }
}
