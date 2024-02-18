package com.hf.jvmtest.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleCalculation {
    public static double divide(double numerator, double denominator) {
        BigDecimal result = BigDecimal.valueOf(numerator)
                .divide(BigDecimal.valueOf(denominator), 10, RoundingMode.HALF_UP);
        return result.doubleValue();
    }
    public static double round(double value, int scale) {
        return BigDecimal.valueOf(value).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }
}
