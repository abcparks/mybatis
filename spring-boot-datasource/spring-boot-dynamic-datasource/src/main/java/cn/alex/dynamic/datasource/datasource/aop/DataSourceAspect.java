package cn.alex.dynamic.datasource.datasource.aop;

import cn.alex.dynamic.datasource.datasource.DataSourceContextHolder;
import cn.alex.dynamic.datasource.datasource.annotation.DS;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Created by WCY on 2022/8/31
 */
@Aspect
@Component
public class DataSourceAspect implements Ordered {

    @Around("within(cn.alex.dynamic.datasource.service.*) && @annotation(ds)")
    public Object dynamicDatasource(ProceedingJoinPoint joinPoint, DS ds) throws Throwable {
        Object result = null;
        try {
            // 设置动态数据源名称
            DataSourceContextHolder.setDataSource(ds.name());
            // 执行目标任务
            result = joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 移除动态数据源名称(防止内存泄漏)
            DataSourceContextHolder.clearDataSource();
        }
        return result;
    }

    // Ordered, 返回值越小优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
