package com.bonacamp.ecasystem.domain.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonacamp.ecasystem.domain.account.service.AccountServiceImpl;

@RestController
@RequestMapping("/")
public class AccountController {
	
	@Autowired 
	AccountServiceImpl accountService;
	
	@GetMapping("clickButton")
	public String clickButton() {
		
		System.out.println("### account Test Clickbutton Start");
		
		String resultString = "";
		
		resultString = accountService.clickButton();
		
		if(resultString == null || resultString == "") {
			resultString = "ERROR";
		}
		
		return resultString;
	}

}
