package com.bonacamp.ecasystem.util.HttpUtils.Service;

import java.util.HashMap;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.bonacamp.ecasystem.util.dataBase64ToJsonObj;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public final class HttpImpl implements HttpService {
	
	@Override
	public String send(String ws_id, String poc_id) {
		
		System.out.println("### HttpImpl Send Start");
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		try {
			
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        	factory.setConnectTimeout(5000);
            factory.setReadTimeout(5000);
            
            HttpClient httpClient = HttpClientBuilder.create()
                    .setMaxConnTotal(50)
                    .setMaxConnPerRoute(20)
                    .build();
            
            factory.setHttpClient(httpClient);

            RestTemplate restTemplate = new RestTemplate(factory);
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

            HttpHeaders header = new HttpHeaders();
            header.add("Accept", "*/*, text/plain");
            header.add("Content-Type", "application/json");
            header.add("Authorization", "Basic ZWFpX2tib246ZWFpX2tib25Ab2IuY28ua3I=");
            header.add("country", "KR");
            header.add("requestTraceld", "20220429153000common_keai_0001.002");
            header.add("timezone", "Asia/Seoul");
            header.add("SourceSystem", "KEAI");

            MultiValueMap<String, String> bodys = new LinkedMultiValueMap<>();
            bodys.add("WS_ID",ws_id);
            bodys.add("POC_ID",poc_id);

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(bodys, header);
            System.out.println("entity : "+entity);

            String url = "https://www.bees-kconnect.com:25005/eai/extras/common_keai_0001";

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build(false);

            ResponseEntity<String> resultToken = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, String.class);
             
            result.put("statusCode", resultToken.getStatusCodeValue()); 
            result.put("header", resultToken.getHeaders()); 
            result.put("body", resultToken.getBody()); 
            
            JSONParser jsonParser = new JSONParser();
    		Object bodyObj = jsonParser.parse(result.get("body").toString());
    		JSONObject jsonBodyObj = (JSONObject) bodyObj;
    		
    		dataBase64ToJsonObj dbtojsonObj = new dataBase64ToJsonObj();
    		String decodeToken = dbtojsonObj.Base64ToJson(jsonBodyObj.get("token").toString());
    		
    		System.out.println("OG Token : "+jsonBodyObj.get("token").toString());
    		System.out.println("Decode Token : "+decodeToken);
    		
    		
    		return jsonBodyObj.get("token").toString();
            
		}catch(Exception e) {
			e.printStackTrace();
			
			return "ERROR";
		}
		

	}
	
   
}