package com.rtm.pabean.utils;

import java.math.BigDecimal;

public class FormulaUtil {

    public static double countBM(String tariffType, double cifIDR, double numberUnit, double tariff) {
        double bmValue = 0.0;
        if ("1".equals(tariffType)) {
            bmValue = cifIDR * (tariff / 100);
        } else {
            bmValue = numberUnit * tariff;
        }
        return bmValue;
    }

    public static long countPPN(double cifIDR, double bmValue, double tariff) {
        double value = (cifIDR + bmValue) * (tariff / 100);
        return (long) value;
    }

    public static double countPPH(double cifIDR, double bmValue, double tariff) {
        double value = (((long) (cifIDR + bmValue) / 1000) * 1000) * (tariff / 100);
        return value;
    }
    
    public static BigDecimal countBK(String tariffType, BigDecimal numberUnit, BigDecimal price, BigDecimal tariff) {
        if ("1".equals(tariffType)) {
            return numberUnit.multiply(price).multiply(tariff.divide(NumberUtil.toBigDecimal(100)));
        } else {
            return numberUnit.multiply(tariff);
        }
    }

    public static BigDecimal countDS(BigDecimal numberUnit, BigDecimal tariff) {
        return numberUnit.multiply(tariff);
    }

    public static BigDecimal countFreight(double fob, double freight) {
        return NumberUtil.toBigDecimal(fob * freight);
    }

    public static BigDecimal countInsurance(double fob, double freight, double insurance) {
        return NumberUtil.toBigDecimal((fob + freight) * insurance);
    }
}
