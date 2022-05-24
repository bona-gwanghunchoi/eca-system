package com.bonacamp.ecasystem.configuration;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

@Data
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
