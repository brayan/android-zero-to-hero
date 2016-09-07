package com.brayanbedritchuk.zerotohero.view.workout_details.presenter;

import android.content.Context;

import com.brayanbedritchuk.zerotohero.model.Exercise;

public interface WorkoutDetailsView {

    Context getActivityContext();

    void updateExerciseListView();

    void showToast(String message);

    void startNewWorkoutActivity();

    void startExerciseDetailsActivity(Exercise exercise);

    void updateTitle(String title);

    void updateVisibilityOfViews();
}
