package com.bonacamp.ecasystem.configuration;

import com.bonacamp.ecasystem.configuration.datasource.BaseDataSource;
import com.bonacamp.ecasystem.configuration.datasource.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@ConditionalOnProperty(prefix = "mybatis.datasource.mysql", name = { "username", "password", "url", "mapperLocation"})
@Configuration
public class MySqlConfig {
    /**
     * Properties 파일에 있는 기준으로 DB 연동에 사용할 DBProperties 생성
     * @return DBProperties DB 연동 정보를 가지고 있는 객체
     */
    @Bean(name = "mySqlProperties")
    @ConfigurationProperties(prefix = "mybatis.datasource.mysql")
    public MybatisProperties mssqlProperties() {
        return new MybatisProperties();
    }

    @Bean(name = "mySqlDataSource")
    public DataSource dataSource(@Qualifier("mySqlProperties") MybatisProperties properties) {
        return BaseDataSource.valueOf(properties);
    }

    @Order(1)
    @Bean(name = "mySqlTxManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("mySqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
