package br.com.sailboat.zerotohero.view.workout.list.presenter;

import android.content.Context;

import br.com.sailboat.zerotohero.model.Workout;

public interface WorkoutListView {

    Context getActivityContext();

    void updateContentViews();

    void showToast(String message);

    void startNewWorkoutActivity();

    void startWorkoutDetailsActivity(Workout workout);

    void updateWorkoutRemoved(int position);

    void startWorkoutDetailsActivityWithAnimation(Workout workout);
}
