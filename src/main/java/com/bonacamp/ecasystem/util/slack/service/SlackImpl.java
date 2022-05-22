package com.bonacamp.ecasystem.util.slack.service;


import com.bonacamp.ecasystem.util.slack.model.SlackMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public final class SlackImpl implements Slack {

    private final SlackClient slackClient;

    @Override
    public void info(String message) {
        log.info(message);
        slackClient.send(new SlackMessage(message).get());
    }

    @Override
    public void error(Exception ex) {
        log.error("error" , ex);
        slackClient.send(new SlackMessage(ex).get());
    }

    @Override
    public void warn(String message) {
        log.warn(message);
        slackClient.send(new SlackMessage(message).get());
    }
}
