package com.brayanbedritchuk.zerotohero.view.exercise_chooser.presenter;

import android.content.Context;

import com.brayanbedritchuk.zerotohero.model.Exercise;

public interface ExerciseChooserView {

    Context getActivityContext();

    void updateExerciseListView();

    void showToast(String message);

    void startNewWorkoutActivity();

    void startExerciseDetailsActivity(Exercise exercise);

    void updateTitle(String title);

    void updateVisibilityOfViews();

    void updateExerciseView(int position);
}
