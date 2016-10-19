package br.com.sailboat.zerotohero.view.workout.details.presenter;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import br.com.sailboat.zerotohero.base.BasePresenter;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.helper.ListHelper;
import br.com.sailboat.zerotohero.helper.LogHelper;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.model.Workout;
import br.com.sailboat.zerotohero.view.async_tasks.LoadExercisesFromWorkoutAsyncTask;
import br.com.sailboat.zerotohero.view.async_tasks.SaveWorkoutAsyncTask;

public class WorkoutDetailsPresenter extends BasePresenter {

    private WorkoutDetailsView view;
    private WorkoutDetailsViewModel viewModel;

    public WorkoutDetailsPresenter(WorkoutDetailsView view) {
        setView(view);
        setViewModel(new WorkoutDetailsViewModel());
    }

//    @Override
//    protected void saveViewModel(Bundle outState) {
//        outState.putSerializable(WorkoutDetailsViewModel.TAG, getViewModel());
//    }
//
//    @Override
//    protected void restoreViewModel(Bundle savedInstanceState) {
//        setViewModel((WorkoutDetailsViewModel) savedInstanceState.getSerializable(WorkoutDetailsViewModel.TAG));
//        getViewModel().setExerciseList(new ArrayList<Exercise>());
//    }

    @Override
    protected void onResumeFirstSession() {
        loadExercises();
    }

    @Override
    protected void postResume() {
        updateContentViews();
    }

    public void onClickNewWorkout() {
        getView().startNewWorkoutActivity();
    }

    public void onClickExercise(int position) {
        Exercise exercise = getExercises().get(position);
        getView().startExerciseDetailsActivity(exercise);
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

    public void onClickMenuDelete() {
        Workout workout = getViewModel().getWorkout();
        getView().closeActivityWithResultOkAndDeleteWorkout(workout);
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

}