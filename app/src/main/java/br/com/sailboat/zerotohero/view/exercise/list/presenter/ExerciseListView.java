package br.com.sailboat.zerotohero.view.exercise.list.presenter;

import android.content.Context;

import br.com.sailboat.zerotohero.model.Exercise;

public interface ExerciseListView {

    Context getActivityContext();

    void updateContentViews();

    void showToast(String message);

    void startExerciseDetailsActivity(Exercise exercise);

    void startNewExerciseActivity();

    void showDialog(String message);

    void updateExerciseRemoved(int position);
}
