package com.bonacamp.ecasystem.util.slack.service;

public interface Slack {

    /**
     * Slack에 정보 메시지 전달 및 log.info 출력
     * @param message
     */
    void info(String message);

    /**
     * Slack에 오류 메시지 전달 및 log.error 출력
     * @param ex
     * @return
     */
    void error(Exception ex);

    /**
     * Slack에 경고 메시지 전달 및 log.warn 출력
     * @param message
     */
    void warn(String message);

}
