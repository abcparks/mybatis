package cn.alex.dynamic.datasource.datasource;

import cn.alex.dynamic.datasource.datasource.constant.DataSourceType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WCY on 2022/8/31
 */
@Primary
@Component
public class DynamicDataSource extends AbstractRoutingDataSource implements InitializingBean {

    @Autowired
    private DataSource primaryDataSource;

    @Autowired
    private DataSource secondaryDataSource;

    /**
     * 返回数据源标识
     * @return 数据源标识
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }

    @Override
    public void afterPropertiesSet() {
        // 为targetDataSources初始化所有数据源
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.PRIMARY, primaryDataSource);
        targetDataSources.put(DataSourceType.SECONDARY, secondaryDataSource);
        super.setTargetDataSources(targetDataSources);

        // 为defaultTargetDataSource设置默认的数据源
        super.setDefaultTargetDataSource(primaryDataSource);

        // 为resolvedDefaultDataSource设置所有数据源
        super.afterPropertiesSet();
    }

}
