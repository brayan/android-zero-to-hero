package com.brayanbedritchuk.zerotohero.view.workout.details.presenter;

import android.content.Context;
import android.content.Intent;

import com.brayanbedritchuk.zerotohero.helper.ExtrasHelper;
import com.brayanbedritchuk.zerotohero.helper.ListHelper;
import com.brayanbedritchuk.zerotohero.helper.LogHelper;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.model.Workout;
import com.brayanbedritchuk.zerotohero.view.async_tasks.LoadExercisesFromWorkoutAsyncTask;
import com.brayanbedritchuk.zerotohero.view.async_tasks.SaveWorkoutAsyncTask;

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
            loadExercises();
        } else {
            updateContentViews();
        }
    }

    private void loadExercises() {
        Context context = getView().getActivityContext().getApplicationContext();
        long workoutId = getViewModel().getWorkout().getId();
        new LoadExercisesFromWorkoutAsyncTask(context, workoutId, new LoadExercisesFromWorkoutAsyncTask.Callback() {
            @Override
            public void onSuccess(List<Exercise> exercises) {
                ListHelper.clearAndAdd(exercises, getViewModel().getExerciseList());
                getView().updateExerciseListView();
            }

            @Override
            public void onFail(Exception e) {
                LogHelper.printExceptionLog(e);
                getView().showToast(e.getMessage());
            }
        }).execute();
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

    public void onClickNavigation() {
        getView().closeActivityWithResultCanceled();
    }

    public void onResultOkEditWorkout(Intent data) {
        getViewModel().setWorkout(ExtrasHelper.getWorkout(data));
        ListHelper.clearAndAdd(ExtrasHelper.getExercises(data), getViewModel().getExerciseList());
        updateContentViews();
        saveWorkout();
    }

    private void saveWorkout() {
        Context context = getView().getActivityContext().getApplicationContext();
        Workout workout = getViewModel().getWorkout();
        List<Exercise> exercises = getViewModel().getExerciseList();

        new SaveWorkoutAsyncTask(context, workout, exercises, new SaveWorkoutAsyncTask.Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onFail(Exception e) {
                LogHelper.printExceptionLog(e);
                getView().showToast(e.getMessage());
            }
        }).execute();
    }

    public void onClickMenuDelete() {
        Workout workout = getViewModel().getWorkout();
        getView().closeActivityWithResultOkAndDeleteWorkout(workout);
    }
}
