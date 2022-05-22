package com.bonacamp.ecasystem.util.slack.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackConfig {

    public static String SLACK_URL;
    public static String SLACK_CHANNEL;

    @Value("${slack.webhook.url}")
    public void setSlackUrl(String url) {
        SLACK_URL = url;
    }

    @Value("${slack.webhook.channel}")
    public void setSlackChannel(String channelName) {
        SLACK_CHANNEL = channelName;
    }
}
