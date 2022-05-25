package com.bonacamp.ecasystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EcaSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcaSystemApplication.class, args);
        System.out.println("Starting ECA System......");
    }

}
