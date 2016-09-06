package com.brayanbedritchuk.zerotohero.view.workout_details.presenter;

import com.brayanbedritchuk.zerotohero.model.entity.Exercise;
import com.brayanbedritchuk.zerotohero.model.viewmodel.WorkoutDetailsViewModel;

import java.util.List;

public class WorkoutDetailsPresenter {

    private WorkoutDetailsView view;
    private WorkoutDetailsViewModel viewModel;

    public WorkoutDetailsPresenter(WorkoutDetailsView view) {
        setView(view);
        setViewModel(new WorkoutDetailsViewModel());
    }

    public void onResume() {
        verifyAndLoadWorkouts();
        getViewModel().setFirstSession(false);
    }

    public void onClickNewWorkout() {
        getView().startNewWorkoutActivity();
    }

    public void onClickExercise(int position) {
        Exercise exercise = getExercises().get(position);
        getView().startExerciseDetailsActivity(exercise);
    }

    private void verifyAndLoadWorkouts() {
        if (getViewModel().isFirstSession()) {
            new ExercisesLoader(getView(), getViewModel()).execute();
        } else {
            getView().updateContentViews();
        }
    }

    public WorkoutDetailsViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(WorkoutDetailsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public WorkoutDetailsView getView() {
        return view;
    }

    public void setView(WorkoutDetailsView view) {
        this.view = view;
    }

    public List<Exercise> getExercises() {
        return getViewModel().getExerciseList();
    }

//    public WorkoutDAO getWorkoutDAO() {
//        return WorkoutDAOSQLite.getInstance(getView().getActivityContext());
//    }

}
