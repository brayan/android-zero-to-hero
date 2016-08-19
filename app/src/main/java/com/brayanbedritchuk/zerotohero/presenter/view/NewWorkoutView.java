package com.brayanbedritchuk.zerotohero.presenter.view;

import android.content.Context;

public interface NewWorkoutView {

    Context getActivityContext();

    void updateContentViews();

    void showToast(String message);
}
