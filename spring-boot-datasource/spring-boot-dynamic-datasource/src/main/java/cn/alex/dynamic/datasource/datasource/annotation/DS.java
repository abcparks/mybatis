package cn.alex.dynamic.datasource.datasource.annotation;

import cn.alex.dynamic.datasource.datasource.constant.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by WCY on 2022/8/31
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DS {

    String name() default DataSourceType.PRIMARY;

}
