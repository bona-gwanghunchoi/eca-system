package com.bonacamp.ecasystem.domain.base.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @GetMapping("/health")
    public String heath() {
        return "OK";
    }
}
