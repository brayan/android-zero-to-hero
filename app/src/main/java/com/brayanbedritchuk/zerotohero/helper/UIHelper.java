package com.brayanbedritchuk.zerotohero.helper;

import android.app.Activity;
import android.view.WindowManager;

public class UIHelper {

    public static void hideKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

}
