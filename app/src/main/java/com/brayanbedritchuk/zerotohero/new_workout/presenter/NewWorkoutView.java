package com.brayanbedritchuk.zerotohero.new_workout.presenter;

import android.content.Context;

public interface NewWorkoutView {

    Context getActivityContext();

    void updateContentViews();

    void showToast(String message);
}
