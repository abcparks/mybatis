package cn.alex.multiple.datasource.datasource;

import cn.alex.multiple.datasource.constant.TransactionManagerConstant;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * Created by WCY on 2022/8/31
 */
@Configuration
@MapperScan(basePackages = PrimaryDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "primarySqlSessionFactory")
// 注意: @ConfigurationProperties注解的方式注入需要为每个注入的字段添加setter方法
@ConfigurationProperties(prefix = "spring.datasource.primary")
public class PrimaryDataSourceConfig {

    // 精确到primary目录, 与其它数据源隔离
    public static final String PACKAGE = "cn.alex.multiple.datasource.mapper.primary";
    public static final String MAPPER_LOCATION = "classpath*:mapper/primary/**/*.xml";

    //@Value("${spring.datasource.primary.driver-class-name}")
    private String driverClassName;

    private String url;

    private String username;

    private String password;

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Primary
    @Bean(name = "primarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource primaryDataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(primaryDataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(PrimaryDataSourceConfig.MAPPER_LOCATION));
        return sqlSessionFactory.getObject();
    }


    @Bean(name = TransactionManagerConstant.PRIMARY_TRANSACTION_MANAGER)
    public DataSourceTransactionManager primaryTransactionManager() {
        return new DataSourceTransactionManager(primaryDataSource());
    }

    @Bean(name = "primaryTransactionTemplate")
    public TransactionTemplate primaryTransactionTemplate() {
        return new TransactionTemplate(primaryTransactionManager());
    }

}
