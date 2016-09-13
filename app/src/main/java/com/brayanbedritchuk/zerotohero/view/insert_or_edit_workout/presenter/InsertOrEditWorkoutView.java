package com.brayanbedritchuk.zerotohero.view.insert_or_edit_workout.presenter;

import android.content.Context;

public interface InsertOrEditWorkoutView {

    Context getActivityContext();

    void updateContentViews();

    void showToast(String message);

    void startExercisesChooserActivity();
}
