package com.brayanbedritchuk.zerotohero.helper;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DecimalHelper {

    public static String getReais(double valor) {
        double d1 = 1.234567;
        double d2 = 2;
        NumberFormat nf = new DecimalFormat("'R$'#.##,##");
        return nf.format(d2);
    }
}
