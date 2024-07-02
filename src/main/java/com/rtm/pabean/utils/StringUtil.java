package com.rtm.pabean.utils;

public class StringUtil {

    public static String toString(Object object) {
        if (object == null) {
            return "";
        }
        return object.toString();
    }

    public static String fixNpwp(String value) {
        if (value == null) {
            return null;
        }
        return value.replaceAll("[^\\d]", "");
    }
}
