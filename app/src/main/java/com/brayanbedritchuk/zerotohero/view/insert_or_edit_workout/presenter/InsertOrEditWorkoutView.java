package com.brayanbedritchuk.zerotohero.view.insert_or_edit_workout.presenter;

import android.content.Context;

import com.brayanbedritchuk.zerotohero.model.Exercise;

import java.util.ArrayList;

public interface InsertOrEditWorkoutView {

    Context getActivityContext();

    void updateContentViews();

    void showToast(String message);

    void startExercisesChooserActivity(ArrayList<Exercise> exercises);
}
