package com.rtm.pabean.enums;

public enum TrackingStatusEnum {
    
    QUEUE("RT0"), DRAFT_PENGAJUAN("RT1"), SENT_CEISA("RT2"), SUCCESS_CEISA("RT3"), ERROR_CEISA("RT4"), PENGAJUAN_REVISI("RT5"),
    BATAL_PENGAJUAN("RT6"), REJECT("120");

    private String code;

    private TrackingStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
