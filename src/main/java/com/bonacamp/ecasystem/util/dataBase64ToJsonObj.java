package com.bonacamp.ecasystem.util;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class dataBase64ToJsonObj {
	
	public String Base64ToJson(String jsonStr) throws Exception {
		
		String conStr = "";
		
		try {
			
			conStr = new String(Base64.decodeBase64(jsonStr),"UTF-8");
			
		}catch (Exception ex) {
			
			ex.printStackTrace();
			conStr = "NOTCONV";
			
		}
		return conStr;
	}
	
	public JsonObject getJsonObj(String orgJson) {
		
		JsonObject jObj = new JsonObject();
		jObj = new JsonParser().parse(orgJson).getAsJsonObject();
		
		return jObj;
	}

}
