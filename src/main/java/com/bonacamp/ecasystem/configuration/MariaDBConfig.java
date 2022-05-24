package com.bonacamp.ecasystem.configuration;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.bonacamp.ecasystem.domain.*.repository",
        transactionManagerRef = "mariaDbTransactionManager",
        entityManagerFactoryRef = "mariaDbEntityManagerFactory"
)
@Configuration
public class MariaDBConfig {
    @Primary
    @Bean(name = "mariaDbDataSource")
    @ConfigurationProperties("spring.data.mariadb")
    public DataSource mariaDBDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "mariaDbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("mariaDbDataSource") DataSource dataSource) {
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.implicit_naming_strategy", "org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl");
        map.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        map.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        return builder.dataSource(dataSource)
                .packages("com.bonacamp.ecasystem.domain.*.entity")
                .properties(map)
                .build();
    }

    @Primary
    @Bean(name = "mariaDbTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("mariaDbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
