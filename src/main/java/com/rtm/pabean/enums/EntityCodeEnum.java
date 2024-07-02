package com.rtm.pabean.enums;

public enum EntityCodeEnum {
    
    IMPORTIR("1"), EKSPORTIR("2"), PENGUSAHA("3"), PPJK("4"), PEMASOK("5"), PEMBELI("6"), PEMILIK("7"), PENERIMA("8"), PENGIRIM("9"), PENJUAL("10"), PEMUSATAN("11");

    private String code;

    private EntityCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
