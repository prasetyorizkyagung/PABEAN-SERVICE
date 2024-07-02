package com.rtm.pabean.enums;

public enum JobOrderStatusEnum {
    
    DRAFT(1), SUBMIT(2), BATAL(3), ERR_PARSING(4), SUC_PARSING(5);

    private int code;

    private JobOrderStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
