package br.com.sailboat.zerotohero.view.workout.insert_or_edit.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.model.Workout;

public interface InsertOrEditWorkoutView {

    Context getActivityContext();

    void updateVisibilityOfViews();

    void updateExercisesListAndVisibility();

    void showToast(String message);

    void startExercisesChooserActivity(ArrayList<Exercise> exercises);

    String getTextFromWorkoutName();

    void showDialog(String message);

    void closeActivityWithResultCanceled();

    void closeActivityWithResultOk(Workout workout, List<Exercise> exercises);

    void updateWorkoutNameView(String name);

    void hideKeyboard();

    void updateToolbarTitle(String title);
}
