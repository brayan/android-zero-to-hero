package br.com.sailboat.zerotohero.view.ui.workout.details.presenter;

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
import br.com.sailboat.zerotohero.view.ui.workout.details.view_model.WorkoutDetailsViewModel;

public class WorkoutDetailsPresenter extends BasePresenter {

    private View view;
    private WorkoutDetailsViewModel viewModel;

    public WorkoutDetailsPresenter(View view) {
        setView(view);
        setViewModel(new WorkoutDetailsViewModel());
    }

    @Override
    protected void onResumeFirstSession() {
        loadExercises();
    }

    @Override
    protected void postResume() {
        updateContentViews();
    }

    @Override
    public void extractExtrasFromIntent(Intent intent) {
        Workout workout = ExtrasHelper.getWorkout(intent);
        getViewModel().setWorkout(workout);
    }

    public void onClickEditWorkout() {
        Workout workout = getViewModel().getWorkout();
        getView().startEditWorkoutActivity(workout);
    }

    public void onClickNavigation() {
        getView().closeActivityWithResultCanceled();
    }

    public void onClickMenuDelete() {
        Workout workout = getViewModel().getWorkout();
        getView().closeActivityWithResultOkAndDeleteWorkout(workout);
    }

    public void onResultOkEditWorkout(Intent data) {
        getViewModel().setWorkout(ExtrasHelper.getWorkout(data));
        ListHelper.clearAndAdd(ExtrasHelper.getExercises(data), getViewModel().getExerciseList());
        updateContentViews();
        saveWorkout();
    }

    private void loadExercises() {
        long workoutId = getViewModel().getWorkout().getId();

        new LoadExercisesFromWorkoutAsyncTask(getContext(), workoutId, new LoadExercisesFromWorkoutAsyncTask.Callback() {
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

    private void saveWorkout() {
        Workout workout = getViewModel().getWorkout();
        List<Exercise> exercises = getViewModel().getExerciseList();

        new SaveWorkoutAsyncTask(getContext(), workout, exercises, new SaveWorkoutAsyncTask.Callback() {

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

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<Exercise> getExercises() {
        return getViewModel().getExerciseList();
    }

    public Context getContext() {
        return getView().getActivityContext();
    }



    public interface View {

        Context getActivityContext();
        void updateExerciseListView();
        void showToast(String message);
        void updateTitle(String title);
        void updateVisibilityOfViews();
        void startEditWorkoutActivity(Workout workout);
        void closeActivityWithResultCanceled();
        void closeActivityWithResultOkAndDeleteWorkout(Workout workout);
    }

}
