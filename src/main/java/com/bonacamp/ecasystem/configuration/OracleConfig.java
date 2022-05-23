package com.bonacamp.ecasystem.configuration;

import com.bonacamp.ecasystem.configuration.datasource.BaseDataSource;
import com.bonacamp.ecasystem.configuration.datasource.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@ConditionalOnProperty(prefix = "mybatis.datasource.oracle", name = { "username", "password", "url", "mapperLocation"})
@EnableConfigurationProperties({MybatisProperties.class})
@Configuration
public class OracleConfig {
    /**
     * Properties 파일에 있는 기준으로 DB 연동에 사용할 DBProperties 생성
     * @return DBProperties DB 연동 정보를 가지고 있는 객체
     */
    @Bean(name = "oracleProperties")
    @ConfigurationProperties(prefix = "mybatis.datasource.oracle")
    public MybatisProperties oracleProperties() {
        return new MybatisProperties();
    }

    @Bean(name = "oracleDataSource")
    public DataSource dataSource(@Qualifier("oracleProperties") MybatisProperties properties) {
        return BaseDataSource.valueOf(properties);
    }

    @Order(1)
    @Bean(name = "oracleTxManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("oracleDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
