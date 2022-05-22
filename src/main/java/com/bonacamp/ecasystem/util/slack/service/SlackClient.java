package com.bonacamp.ecasystem.util.slack.service;

import com.bonacamp.ecasystem.util.slack.configuration.SlackConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class SlackClient {

    private SlackClient() { }

    public static void send(HttpEntity<Map<String,Object>> entity) {
        new RestTemplate().exchange(SlackConfig.SLACK_URL, HttpMethod.POST, entity, String.class);
    }
}
