package com.brayanbedritchuk.zerotohero.view.workout.list.presenter;

import android.content.Context;

import com.brayanbedritchuk.zerotohero.model.Workout;

public interface WorkoutListView {

    Context getActivityContext();

    void updateContentViews();

    void showToast(String message);

    void startNewWorkoutActivity();

    void startWorkoutDetailsActivity(Workout workout);
}
