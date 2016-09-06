package com.brayanbedritchuk.zerotohero.view.workout_details.presenter;

import android.content.Context;

import com.brayanbedritchuk.zerotohero.model.entity.Exercise;

public interface WorkoutDetailsView {

    Context getActivityContext();

    void updateContentViews();

    void showToast(String message);

    void startNewWorkoutActivity();

    void startExerciseDetailsActivity(Exercise exercise);
}
