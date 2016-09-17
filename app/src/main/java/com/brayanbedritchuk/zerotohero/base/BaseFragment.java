package com.brayanbedritchuk.zerotohero.base;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            onActivityResultOk(requestCode, data);

        } else if (resultCode == Activity.RESULT_CANCELED) {
            onActivityResultCanceled(requestCode, data);
        }
    }

    protected void onActivityResultOk(int requestCode, Intent data) {
    }

    protected void onActivityResultCanceled(int requestCode, Intent data) {
    }
}
