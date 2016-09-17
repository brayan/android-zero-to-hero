package com.brayanbedritchuk.zerotohero.view.workout.insert_or_edit.presenter;

import android.content.Context;

import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.model.Workout;

import java.util.ArrayList;
import java.util.List;

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
