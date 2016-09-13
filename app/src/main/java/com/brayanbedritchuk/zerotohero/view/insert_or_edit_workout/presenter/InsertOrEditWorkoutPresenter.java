package com.brayanbedritchuk.zerotohero.view.insert_or_edit_workout.presenter;

import com.brayanbedritchuk.zerotohero.model.Exercise;

import java.util.List;

public class InsertOrEditWorkoutPresenter {

    private InsertOrEditWorkoutView view;
    private InsertOrEditWorkoutViewModel viewModel;

    public InsertOrEditWorkoutPresenter(InsertOrEditWorkoutView view) {
        setView(view);
        setViewModel(new InsertOrEditWorkoutViewModel());
    }

    public void onResume() {
        getViewModel().setFirstSession(false);
    }

    public void onClickAddExercises() {
        getView().startExercisesChooserActivity();
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

}
