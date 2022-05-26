package com.bonacamp.ecasystem.domain.base.dto;

public final class ResponseDto {
    private final int type;
    private final String message;
    private final long timestamp = System.currentTimeMillis();

    public ResponseDto(String message) {
        this.type = 1;
        this.message = message;
    }

    public ResponseDto(int type, String message) {
        this.type = type;
        this.message = message;
    }
}
