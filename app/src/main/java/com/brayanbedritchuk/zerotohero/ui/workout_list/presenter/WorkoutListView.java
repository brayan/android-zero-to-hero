package com.brayanbedritchuk.zerotohero.ui.workout_list.presenter;

import android.content.Context;

public interface WorkoutListView {

    Context getActivityContext();

    void updateContentViews();

    void showToast(String message);

    void startNewWorkoutActivity();
}
