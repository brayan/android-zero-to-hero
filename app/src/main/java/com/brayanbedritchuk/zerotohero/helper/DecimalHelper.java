package com.brayanbedritchuk.zerotohero.helper;

import java.text.DecimalFormat;

public class DecimalHelper {

    private static final String PATTERN_REAIS = "'R$'###.###.###.###.###,##";

    public static String getReais(double valor) {
        return new DecimalFormat(PATTERN_REAIS).format(valor);
    }
}
