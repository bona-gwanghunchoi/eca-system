package com.bonacamp.ecasystem.configuration;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.data.oracle")
public class MyBatisProperties extends DataSourceProperties {
    /**
     * MyBatis config mapper-locations 경로 설정
     * resources/mybatis/mapper/mysql/*.xml
     */
    private String mapperLocation;
    /**
     * MyBatis config-locations 설정
     * resources/mybatis/mybatis-config.xml
     */
    private String configLocation;
}
