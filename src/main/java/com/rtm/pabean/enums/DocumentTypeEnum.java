package com.rtm.pabean.enums;

public enum DocumentTypeEnum {
    
    BC20("20"), BC30("30");

    private String code;

    private DocumentTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
