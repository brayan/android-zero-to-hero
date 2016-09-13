package com.brayanbedritchuk.zerotohero.view.exercise_chooser.presenter;

import android.util.SparseArray;

import com.brayanbedritchuk.zerotohero.model.Exercise;

import java.util.List;

public class ExerciseChooserPresenter {

    private ExerciseChooserView view;
    private ExerciseChooserViewModel viewModel;

    public ExerciseChooserPresenter(ExerciseChooserView view) {
        setView(view);
        setViewModel(new ExerciseChooserViewModel());
    }

    public void onResume() {
        updateTitle();
        verifyAndLoadWorkouts();
        getViewModel().setFirstSession(false);
    }

    public void onClickNewWorkout() {
        getView().startNewWorkoutActivity();
    }

    public void onClickExercise(int position) {
        Exercise exercise = getExerciseList().get(position);

        if (getViewModel().getSelectedExercises().get(exercise.getId()) == null) {
            getViewModel().getSelectedExercises().put(exercise.getId(), exercise);
        } else {
            getViewModel().getSelectedExercises().remove(exercise.getId());
        }

        updateTitle();
        getView().updateExerciseView(position);
    }

    private void updateTitle() {
        int size = getViewModel().getSelectedExercises().size();

        if (size == 0) {
            getView().updateTitle("Select one or more exercises");
        } else if (size == 1) {
            getView().updateTitle("1 exercise");
        } else {
            getView().updateTitle(size + " exercises");
        }
    }

    private void verifyAndLoadWorkouts() {
        if (getViewModel().isFirstSession()) {
            new ExercisesLoader(getView(), getViewModel()).execute();
        } else {
            updateContentViews();
        }
    }

    private void updateContentViews() {
        getView().updateExerciseListView();
        getView().updateVisibilityOfViews();
    }

    public ExerciseChooserViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(ExerciseChooserViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public ExerciseChooserView getView() {
        return view;
    }

    public void setView(ExerciseChooserView view) {
        this.view = view;
    }

    public List<Exercise> getExerciseList() {
        return getViewModel().getExerciseList();
    }

    public SparseArray<Exercise> getSelectedExercises() {
        return getViewModel().getSelectedExercises();
    }

//    public WorkoutDAO getWorkoutDAO() {
//        return WorkoutDAOSQLite.getInstance(getView().getActivityContext());
//    }

}
