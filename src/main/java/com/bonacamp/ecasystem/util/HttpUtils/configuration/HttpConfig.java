package com.bonacamp.ecasystem.util.HttpUtils.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "httptemplate")
public class HttpConfig {
	
	private String ws_id;
    private String poc_id;
    
    
	public String getWs_id() {
		return ws_id;
	}
	public void setWs_id(String ws_id) {
		this.ws_id = ws_id;
	}
	public String getPoc_id() {
		return poc_id;
	}
	public void setPoc_id(String poc_id) {
		this.poc_id = poc_id;
	}
    
    

	

}
