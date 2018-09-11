package com.appleyk.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


//import org.springframework.boot.jdbc.DataSourceBuilder;

/**
 * 注入数据源【bean】
 * @Description
 * @Author Appleyk
 * @Blob https://blog.csdn.net/appleyk
 * @Date Created on 上午 10:03 2018-8-31
 */
@EnableTransactionManagement  // 开启事务支持
@Configuration
// 动态扫描Mapper所在的包（避免一个一个的mapper接口单独加@Mapper注解）
@MapperScan(basePackages = "com.appleyk.mapper", sqlSessionTemplateRef  = "masterSqlSessionTemplate")
public class DBConfig {

    @Primary
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource masterDataSource() {

       // Spring Boot还提供了一个实用程序构建器类DataSourceBuilder
       // 可用于构建一个标准数据源(如果它在类路径上)
       // 构建器可以根据类路径中可用的内容检测要使用的类。它还可以基于JDBC url自动检测驱动程序。
        return DataSourceBuilder.create().build();
    }

    // 注入Session工厂，利用SqlSessionFactory创建并注册SqlSessionTemplate
    // SqlSessionTemplate是SqlSession的实现类，较SqlSession的默认实现类DefaultSqlSession来说，是线程安全的
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*/*.xml"));
        return bean.getObject();
    }

    // 配置事务管理器
    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager mastertransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // SqlSessionTemplate是Mybatis的核心，管理session，和调用sql方法
    // 会保证使用的SqlSession是和当前Spring的事务相关的。
    // 它管理session的生命周期，包含必要的关闭，提交或回滚操作
    @Bean(name = "masterSqlSessionTemplate")
    public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
