package com.bonacamp.ecasystem.annotation.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 프로젝트에서 사용하는 Security 관련 Annotation을 관리
 */
public class SecurityAnnotation {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Token {
    }
}
