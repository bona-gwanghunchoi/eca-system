package com.bonacamp.ecasystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonacamp.ecasystem.service.DbService;



@Controller
@RequestMapping("/")
public class AOPController {
	
	@Autowired 
	DbService dbService;
	
	@GetMapping("clickButton")
	@ResponseBody
	public String clickButton() {
		
		String resultString = "";
		
		resultString = dbService.clickButton();
		
		if(resultString == null || resultString == "") {
			resultString = "ERROR";
		}
		
		return resultString;
	}

}
