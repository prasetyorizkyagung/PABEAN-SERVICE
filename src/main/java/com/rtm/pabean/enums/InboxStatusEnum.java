package com.rtm.pabean.enums;

public enum InboxStatusEnum {
    
    QUEUE("00"), HANDLED("10"), ERROR("11"), DONE("20");

    private String code;

    private InboxStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
