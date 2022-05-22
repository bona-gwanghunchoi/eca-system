package com.bonacamp.ecasystem.util.slack.model;

import com.bonacamp.ecasystem.util.slack.configuration.SlackConfig;
import org.springframework.http.HttpEntity;

import java.util.HashMap;
import java.util.Map;

public final class SlackMessage {
    private final static String CHANNEL_NAME = "#" + SlackConfig.SLACK_CHANNEL;
    private final static String SLACK_NAME = "알리미";
    private String requestMessage;

    public SlackMessage(String message) {
        requestMessage = message;
    }

    public SlackMessage(Exception ex) {
        requestMessage = ex.getMessage();
    }

    public HttpEntity<Map<String,Object>> get(){

        Map<String,Object> setMessage = new HashMap<>();
        setMessage.put("channel", CHANNEL_NAME);
        setMessage.put("text", requestMessage);
        setMessage.put("username", SLACK_NAME);

        HttpEntity<Map<String,Object>> entity = new HttpEntity<>(setMessage);

        return entity;
    }
}
