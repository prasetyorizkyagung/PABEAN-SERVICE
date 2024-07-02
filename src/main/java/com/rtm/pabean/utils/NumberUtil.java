package com.rtm.pabean.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Optional;

public class NumberUtil {

    public static BigDecimal toBigDecimal(int value) {
        return new BigDecimal(value);
    }

    public static BigDecimal toBigDecimal(Double value) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(value);
    }

    public static BigDecimal toBigDecimal(Object value) {
        if (Optional.ofNullable(value).orElse("").toString().trim().isEmpty()) {
            return BigDecimal.ZERO;
        }
        try {
            return new BigDecimal(value.toString().trim());
        } catch (NumberFormatException e) {
            e.getLocalizedMessage();
            return BigDecimal.ZERO;
        }
    }

    public static BigInteger toBigInteger(Integer value) {
        if (value == null) {
            return BigInteger.ZERO;
        }
        return BigInteger.valueOf(value);
    }

    public static BigInteger toBigInteger(Object value) {
        if (Optional.ofNullable(value).orElse("").toString().trim().isEmpty()) {
            return BigInteger.ZERO;
        }
        try {
            return new BigInteger(value.toString().trim());
        } catch (NumberFormatException e) {
            e.getLocalizedMessage();
            return BigInteger.ZERO;
        }
    }

    public static BigInteger toBigInteger(Short value) {
        if (value == null) {
            return BigInteger.ZERO;
        }
        return BigInteger.valueOf(value);
    }

    public static BigDecimal round2Decimal(BigDecimal value) {
        return value.setScale(2, RoundingMode.UP);
    }

    public static int toInt(Object obj) {
        if (Optional.ofNullable(obj).orElse("").toString().trim().isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(obj.toString().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static double toDouble(BigDecimal value) {
        if (value == null) {
            return 0.0;
        }
        return value.doubleValue();
    }

    public static double fixDouble(Double value) {
        return Optional.ofNullable(value).orElse(0.0);
    }

    public static BigDecimal round(BigDecimal value) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal round(BigDecimal value, int scale, RoundingMode roundingMode) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        double temp = value.doubleValue() - value.longValue();
        if (temp == 0.0) {
            return value;
        } else {
            return value.setScale(scale, roundingMode);
        }
    }

    public static BigDecimal setScale(int scale, BigDecimal value, RoundingMode roundingMode) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return value.setScale(scale, roundingMode);
    }
}
