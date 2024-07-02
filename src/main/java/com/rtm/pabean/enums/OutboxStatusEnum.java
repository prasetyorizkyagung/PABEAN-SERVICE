package com.rtm.pabean.enums;

public enum OutboxStatusEnum {
    
    QUEUE("00"), HANDLED("10"), ERROR("11"), DONE("20");

    private String code;

    private OutboxStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
