package cn.alex.multiple.datasource.datasource.annotation;

import cn.alex.multiple.datasource.constant.TransactionManagerConstant;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by WCY on 2022/9/4
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiTransactional {

    @AliasFor("transactionManager")
    String[] value() default {TransactionManagerConstant.PRIMARY_TRANSACTION_MANAGER};

    @AliasFor("value")
    String[] transactionManager() default {TransactionManagerConstant.PRIMARY_TRANSACTION_MANAGER};

}
