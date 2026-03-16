package cn.alex.multiple.datasource.datasource;

import cn.alex.multiple.datasource.constant.TransactionManagerConstant;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * Created by WCY on 2022/8/31
 */
@Configuration
@MapperScan(basePackages = SecondaryDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "secondarySqlSessionFactory")
public class SecondaryDataSourceConfig {

    // 精确到secondary目录, 与其它数据源隔离
    public static final String PACKAGE = "cn.alex.multiple.datasource.mapper.secondary";
    public static final String MAPPER_LOCATION = "classpath*:mapper/secondary/**/*.xml";

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "secondarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("secondaryDataSource") DataSource secondaryDataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(secondaryDataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(SecondaryDataSourceConfig.MAPPER_LOCATION));
        return sqlSessionFactory.getObject();
    }

    @Bean(name = TransactionManagerConstant.SECONDARY_TRANSACTION_MANAGER)
    public DataSourceTransactionManager secondaryTransactionManager() {
        return new DataSourceTransactionManager(secondaryDataSource());
    }

    @Bean(name = "secondaryTransactionTemplate")
    public TransactionTemplate secondaryTransactionTemplate() {
        return new TransactionTemplate(secondaryTransactionManager());
    }
}
