package com.bonacamp.ecasystem.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Application 환경 설정
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {

    /**
     * API 기본 주소 - 설정 파일
     */
    public static String BASE_URL;

    @Value("${server.baseUrl}")
    public void setBaseUrl(String url) {
        this.BASE_URL = url;
    }
    /**
     *  API BASE URL 설정
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(AppConfig.BASE_URL, HandlerTypePredicate.forAnnotation(RestController.class));
    }
    
    
}
