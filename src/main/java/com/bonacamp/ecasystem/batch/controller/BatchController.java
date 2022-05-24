package com.bonacamp.ecasystem.batch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 배치 서비스를 수동으로 실행하는 컨트롤러
 */
@RequestMapping("/batch")
@RestController
public class BatchController {

    @GetMapping("/health")
    public String heath() {
        // TEST : http://localhost:8080/api/v1/batch/health
        return "OK";
    }
}
