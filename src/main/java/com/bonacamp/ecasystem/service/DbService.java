package com.bonacamp.ecasystem.service;

import org.springframework.stereotype.Service;

import com.bonacamp.ecasystem.annotation.CustomAnnotation.commonSecurityKey;

@Service("dbService")
public class DbService {
	
    @commonSecurityKey
	public String clickButton() {
    	
    	System.out.println("### AFTER Annotation");
    	
    	return "OK";
	}
    
	
	

}
