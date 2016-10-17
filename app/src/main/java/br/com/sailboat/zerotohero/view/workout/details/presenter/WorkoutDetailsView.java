package br.com.sailboat.zerotohero.view.workout.details.presenter;

import android.content.Context;

import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.model.Workout;

public interface WorkoutDetailsView {

    Context getActivityContext();

    void updateExerciseListView();

    void showToast(String message);

    void startNewWorkoutActivity();

    void startExerciseDetailsActivity(Exercise exercise);

    void updateTitle(String title);

    void updateVisibilityOfViews();

    void startEditWorkoutActivity(Workout workout);

    void closeActivityWithResultCanceled();

    void closeActivityWithResultOkAndDeleteWorkout(Workout workout);
}
