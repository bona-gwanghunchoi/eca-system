package com.bonacamp.ecasystem.configuration;

import com.bonacamp.ecasystem.util.CustomUtils;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.data.oracle", name = { "username", "password", "url"})
@EnableConfigurationProperties({MyBatisProperties.class})
@Configuration
public class OracleConfig {
    // 설정 정보 주입
    private final MyBatisProperties myBatisProperties;

    @Bean(name = "myBatisDatasource")
    public DataSource dataSource() {

        // datasource 설정
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(myBatisProperties.getUrl());
        dataSource.setUsername(myBatisProperties.getUsername());
        dataSource.setPassword(myBatisProperties.getPassword());
        dataSource.setDriverClassName(myBatisProperties.getDriverClassName());

        return new LazyConnectionDataSourceProxy(dataSource);
    }

    @Order(1)
    @Bean(name = "myBatisTxManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("myBatisDatasource") DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "myBatisSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("myBatisDatasource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(applicationContext.getResources(myBatisProperties.getMapperLocation()));

        if (!CustomUtils.isNullOrEmpty(myBatisProperties.getConfigLocation())) {

            factoryBean.setConfigLocation(applicationContext.getResource(myBatisProperties.getConfigLocation()));
        }

        return factoryBean.getObject();
    }

    @Bean(name = "myBatisSessionTemplate", destroyMethod = "clearCache")
    public SqlSessionTemplate sessionTemplate(@Qualifier("myBatisSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {

        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
