package com.brayanbedritchuk.zerotohero.helper;

import android.util.Log;

public class LogHelper {

    private static final String TAG = "ZERO_TO_HERO_LOG";

    public static void printExceptionLog(Exception e) {
        Log.e(TAG, e.getMessage(), e);
    }
}
