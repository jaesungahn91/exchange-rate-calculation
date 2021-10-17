package io.spring.exchangeratecalculation.util;

import java.text.DecimalFormat;

public class FormatUtil {

    public static String decimalFormat(double price) {
        return new DecimalFormat("###,###.##").format(price);
    }
}
