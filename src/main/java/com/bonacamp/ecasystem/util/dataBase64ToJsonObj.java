package com.bonacamp.ecasystem.util;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class dataBase64ToJsonObj {
	//private Logger logger = LoggerFactory.getLogger(this.getClass());
	public String Base64ToJson(String jsonStr) throws Exception {
		String conStr = "";
		try {
			conStr = new String(Base64.decodeBase64(jsonStr));
		}catch (Exception ex) {
			ex.printStackTrace();
			conStr = "NOTCONV";
		}
		//logger.info("AAAAAAA"+conStr);
		return conStr;
	}
	
	public JsonObject getJsonObj(String orgJson) {
		//Gson gson = new Gson();
		JsonObject jObj = new JsonObject();
		jObj = new JsonParser().parse(orgJson).getAsJsonObject();
		return jObj;
	}

}
