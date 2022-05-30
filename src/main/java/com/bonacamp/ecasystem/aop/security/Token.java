package com.bonacamp.ecasystem.aop.security;

import java.util.Optional;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bonacamp.ecasystem.domain.access.entity.AccessInfo;
import com.bonacamp.ecasystem.domain.access.repository.AccessInfoRepository;
import com.bonacamp.ecasystem.util.HttpUtils.Service.HttpService;
import com.bonacamp.ecasystem.util.HttpUtils.configuration.HttpConfig;

@Component
@Aspect
public class Token {

	Logger logger = LoggerFactory.getLogger(Token.class);
	
	@Autowired
	private HttpConfig httpConfig;

	@Autowired
	private HttpService httpService;
	
	@Autowired
	private AccessInfoRepository accessInfoRepository;

	@Before("@annotation(com.bonacamp.ecasystem.annotation.security.SecurityAnnotation.Token)")
	public void commonSecurityKey() throws Throwable {

		System.out.println("WS_ID : " + httpConfig.getWs_id());
		System.out.println("POC_ID : " + httpConfig.getPoc_id());

		String resToken = httpService.send(httpConfig.getWs_id().toString(), httpConfig.getPoc_id().toString());
		
		
		if(!resToken.equals("ERROR")) {
			
			AccessInfo acess = new AccessInfo();
			acess.setSvr_id(Long.valueOf(1));
	        acess.setWs_id(httpConfig.getWs_id());
	        acess.setPoc_id(httpConfig.getPoc_id());
//	        acess.setAccess_token(resToken);
	        acess.setAccess_token("Data too long for column 'access_token' at row");
	        

	        accessInfoRepository.save(acess);
	        
	        Optional<AccessInfo> optional = accessInfoRepository.findById(Long.valueOf(1));
	        if(optional.isPresent()) {
	        	AccessInfo selectOne = optional.get();
	        	System.out.println("selectOne : "+selectOne.toString());
	        } else {
	        	throw new Exception();
	        }
	        
		}
		
		
	}
}
