package com.bonacamp.ecasystem.domain.account.service;

import org.springframework.stereotype.Service;

import com.bonacamp.ecasystem.annotation.security.SecurityAnnotation.Token;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Token
	public String clickButton() {
    	
    	System.out.println("### AFTER Annotation");
    	
    	return "OK";
	}
}
