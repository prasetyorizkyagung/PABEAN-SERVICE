package com.rtm.pabean.enums;

public enum ReferenceCodeEnum {
    
    PAYMENT_METHOD("0001"), PROCEDURE_TYPE("0003"), TPS("0004"), COUNTRY("0005"), TRANSPORT_TYPE("0006"), INCOTERM("0007"),
    TRANSACTION_TYPE("0008"), CURRENCY("0009"), INSURANCE_TYPE("0010"), PACKAGE_TYPE("0011"), API_TYPE("0012"), IDENTITY_TYPE("0013"),
    FACILITY("0014"), CONTAINER_SIZE("0015"), CONTAINER_TYPE("0016"), CONTAINER_CODE("0017"), UNIT_CODE("0018"), ITEM_CONDITION("0019"),
    VD_TYPE("0020"), TARIFF_FACILITY("0021"), TARIFF_TYPE("0022"), QUALITY_CODE("0023"), TRADE_CODE("0024"), BULK_CODE("0025"),
    EXPORT_TYPE("0026"), EXPORT_CATEGORY("0027"), COMODITY("0028"), LOCATION_CODE("0029"), BANK_DHE("0030"), REGION_CODE("0031"),
    ITEM_TYPE("0033"), PART_CODE("0035"), TPS_TYPE("0036");

    private String code;

    private ReferenceCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
