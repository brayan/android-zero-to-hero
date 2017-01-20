package br.com.sailboat.zerotohero.view.ui.workout.details;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.model.Workout;
import br.com.sailboat.zerotohero.view.async_tasks.LoadExercisesFromWorkoutAsyncTask;
import br.com.sailboat.zerotohero.view.async_tasks.SaveWorkoutAsyncTask;

public class WorkoutDetailsPresenter extends BasePresenter<WorkoutDetailsPresenter.View> {

    private WorkoutDetailsViewModel viewModel = new WorkoutDetailsViewModel();

    public WorkoutDetailsPresenter(WorkoutDetailsPresenter.View view) {
        super(view);
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
        Workout workout = Extras.getWorkout(intent);
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
        getViewModel().setWorkout(Extras.getWorkout(data));
        getViewModel().getExerciseList().clear();
        getViewModel().getExerciseList().addAll(Extras.getExercises(data));
        updateContentViews();
        saveWorkout();
    }

    private void loadExercises() {
        long workoutId = getViewModel().getWorkout().getId();

        new LoadExercisesFromWorkoutAsyncTask(getContext(), workoutId, new LoadExercisesFromWorkoutAsyncTask.Callback() {
            @Override
            public void onSuccess(List<Exercise> exercises) {
                getViewModel().getExerciseList().clear();
                getViewModel().getExerciseList().addAll(exercises);
                getView().updateExerciseListView();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
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
                printLogAndShowDialog(e);
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

    private WorkoutDetailsViewModel getViewModel() {
        return viewModel;
    }

    public List<Exercise> getExercises() {
        return getViewModel().getExerciseList();
    }

    public Context getContext() {
        return getView().getActivityContext();
    }



    public interface View extends BasePresenter.View {
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
