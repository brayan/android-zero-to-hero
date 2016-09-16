package com.brayanbedritchuk.zerotohero.view.workout.details.presenter;

import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.model.Workout;

import java.util.List;

public class WorkoutDetailsPresenter {

    private WorkoutDetailsView view;
    private WorkoutDetailsViewModel viewModel;

    public WorkoutDetailsPresenter(WorkoutDetailsView view) {
        setView(view);
        setViewModel(new WorkoutDetailsViewModel());
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
        Exercise exercise = getExercises().get(position);
        getView().startExerciseDetailsActivity(exercise);
    }

    private void verifyAndLoadWorkouts() {
        if (getViewModel().isFirstSession()) {
            new ExercisesLoader(getView(), getViewModel()).execute();
        } else {
            updateContentViews();
        }
    }

    private void updateContentViews() {
        updateTitle();
        getView().updateExerciseListView();
        getView().updateVisibilityOfViews();
    }

    private void updateTitle() {
        getView().updateTitle(getViewModel().getWorkout().getName());
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

    public void onClickEditWorkout() {
        Workout workout = getViewModel().getWorkout();
        getView().startEditWorkoutActivity(workout);
    }

//    public WorkoutDAO getWorkoutDAO() {
//        return WorkoutDAOSQLite.getInstance(getView().getActivityContext());
//    }

}
