package com.brayanbedritchuk.zerotohero.view.workout.insert_or_edit.presenter;

import android.content.Intent;

import com.brayanbedritchuk.zerotohero.helper.Extras;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.model.Workout;

import java.util.ArrayList;
import java.util.List;

public class InsertOrEditWorkoutPresenter {

    private InsertOrEditWorkoutView view;
    private InsertOrEditWorkoutViewModel viewModel;

    public InsertOrEditWorkoutPresenter(InsertOrEditWorkoutView view) {
        setView(view);
        setViewModel(new InsertOrEditWorkoutViewModel());
    }

    public void onResume() {
        if (getViewModel().isFirstSession() && getViewModel().getWorkout() != null) {
            // LOAD ARRAY_LIST_EXERCISES FOR THIS FUCKING WORKOUT!

        } else {
            getView().updateContentViews();
        }

        getViewModel().setFirstSession(false);
    }

    public void onClickAddExercises() {
        ArrayList array = (ArrayList) getViewModel().getExercises();
        getView().startExercisesChooserActivity(array);
    }

    public void onResultOkExerciseChooser(Intent data) {
        List<Exercise> exercises = getSelectedExercisesFromIntent(data);
        clearAndUpdateSelectedExercises(exercises);
        getView().updateContentViews();
    }

    private ArrayList<Exercise> getSelectedExercisesFromIntent(Intent data) {
        return (ArrayList<Exercise>) data.getSerializableExtra(Extras.ARRAY_LIST_EXERCISES);
    }

    private void clearAndUpdateSelectedExercises(List<Exercise> selectedExercises) {
        getViewModel().getExercises().clear();
        getViewModel().getExercises().addAll(selectedExercises);
    }

    public InsertOrEditWorkoutViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(InsertOrEditWorkoutViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public InsertOrEditWorkoutView getView() {
        return view;
    }

    public void setView(InsertOrEditWorkoutView view) {
        this.view = view;
    }

    public List<Exercise> getExercises() {
        return getViewModel().getExercises();
    }

    public void onClickMenuSave() {
        new SaveWorkoutAsyncTask(getView(), getViewModel()).execute();
    }

    public void onReceiveWorkout(Workout workout) {
        getViewModel().setWorkout(workout);
    }
}
