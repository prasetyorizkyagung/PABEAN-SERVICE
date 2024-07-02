package com.rtm.pabean.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ConvertUtil {

    public static String convertTariffFacilityCode(String code) {
        Map<String, String> mapCode = new HashMap<>();
        mapCode.put("0", "1");
        mapCode.put("1", "2");
        mapCode.put("2", "9");
        mapCode.put("3", "4");
        mapCode.put("4", "5");
        mapCode.put("5", "");
        mapCode.put("6", "6");
        if (Optional.ofNullable(code).orElse("").trim().isEmpty()) {
            code = "0";
        }
        return mapCode.get(code);
    }

    public static String convertCodeFacilityTariff(String code) {
        Map<String, String> mapCode = new HashMap<>();
        mapCode.put("1", "0");
        mapCode.put("2", "1");
        mapCode.put("9", "2");
        mapCode.put("4", "3");
        mapCode.put("5", "4");
        mapCode.put("", "5");
        mapCode.put("6", "6");
        code = Optional.ofNullable(code).orElse("").trim();
        return mapCode.get(code);
    }

    public static String convertCodeFacility(String code) {
        Map<String, String> mapCode = new HashMap<>();
        mapCode.put("BM", "1");
        mapCode.put("PPN", "2");
        mapCode.put("PPNBM", "3");
        mapCode.put("PPH", "4");
        mapCode.put("BMT", "10");
        if (Optional.ofNullable(code).orElse("").trim().isEmpty()) {
            code = "BM";
        }
        return mapCode.get(code);
    }

    public static String convertResponseCodeToModule(String code) {
        Map<String, String> map = new HashMap<>();
        map.put("2000", "100");
        map.put("2001", "110");
        map.put("2016", "200");
        map.put("2020", "211");
        map.put("2080", "250");
        map.put("2015", "260");
        map.put("2016", "270");
        map.put("2003", "300");
        map.put("2005", "400");
        map.put("2004", "420");
        map.put("2006", "450");
        map.put("2026", "500");
        map.put("2013", "510");
        map.put("2014", "520");
        map.put("2090", "900");
        map.put("3010", "10"); //NPP
        map.put("3020", "15"); //PEB Dalam Proses
        map.put("3091", "17"); //NPPD
        map.put("3014", "20"); //PPB
        map.put("3015", "30"); //NPE
        map.put("30250", "38"); //NPKR
        map.put("30360", "40"); //LPE
        map.put("3012", "45"); //NPP PKBE
        map.put("3030", "60"); //NPP Notul PEB
        map.put("3071", "61"); //NPP BCF Container
        map.put("3032", "65"); //NPP Notul PKBE
        map.put("3040", "70"); //Persetujuan Notul PEB
        map.put("3061", "71"); //? Penerimaan BCF Container
        map.put("3042", "75"); //Penerimaan Notul PKBE
        map.put("30201", "90"); //SPPBK
        map.put("3090", "900"); //Respon Umum
        if (Optional.ofNullable(code).orElse("").trim().isEmpty()) {
            return "000";
        }
        return map.get(code);
    }

    public static String convertResponseCodeToStatus(String code) {
        Map<String, String> map = new HashMap<>();
        map.put("2020", "081");
        map.put("2021", "090");
        map.put("2005", "100");
        map.put("2004", "105");
        map.put("2003", "110");
        map.put("2026", "120");
        map.put("2014", "122");
        if (Optional.ofNullable(code).orElse("").trim().isEmpty()) {
            return "040";
        }
        return map.get(code);
    }
    
    public static String convertStuffingCode(String code){
        Map<String, String> map = new HashMap<>();
        map.put("E", "4");
        map.put("L", "7");
        map.put("F", "8");
        if (Optional.ofNullable(code).orElse("").trim().isEmpty()) {
            return "";
        }
        return map.get(code);
    }
}
