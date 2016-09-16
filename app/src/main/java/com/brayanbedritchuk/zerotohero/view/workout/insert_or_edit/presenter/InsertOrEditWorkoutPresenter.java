package com.brayanbedritchuk.zerotohero.view.workout.insert_or_edit.presenter;

import android.content.Intent;

import com.brayanbedritchuk.zerotohero.helper.Extras;
import com.brayanbedritchuk.zerotohero.model.Exercise;

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
        getView().updateContentViews();
        getViewModel().setFirstSession(false);
    }

    public void onClickAddExercises() {
        ArrayList array = (ArrayList) getViewModel().getSelectedExercises();
        getView().startExercisesChooserActivity(array);
    }

    public void onResultOkExerciseChooser(Intent data) {
        List<Exercise> exercises = getSelectedExercisesFromIntent(data);
        clearAndUpdateSelectedExercises(exercises);
        getView().updateContentViews();
    }

    private ArrayList<Exercise> getSelectedExercisesFromIntent(Intent data) {
        return (ArrayList<Exercise>) data.getSerializableExtra(Extras.SELECTED_EXERCISES);
    }

    private void clearAndUpdateSelectedExercises(List<Exercise> selectedExercises) {
        getViewModel().getSelectedExercises().clear();
        getViewModel().getSelectedExercises().addAll(selectedExercises);
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
        return getViewModel().getSelectedExercises();
    }

    public void onClickMenuSave() {
        new SaveWorkoutAsyncTask(getView(), getViewModel()).execute();
    }

}
