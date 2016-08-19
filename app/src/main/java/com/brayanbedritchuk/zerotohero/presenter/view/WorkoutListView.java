package com.brayanbedritchuk.zerotohero.presenter.view;

import android.content.Context;

public interface WorkoutListView {

    Context getActivityContext();

    void updateContentViews();

    void showToast(String message);

    void startNewWorkoutActivity();
}
