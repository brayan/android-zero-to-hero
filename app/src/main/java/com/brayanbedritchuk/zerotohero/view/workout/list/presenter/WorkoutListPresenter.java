package com.brayanbedritchuk.zerotohero.view.workout.list.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.brayanbedritchuk.zerotohero.base.BasePresenter;
import com.brayanbedritchuk.zerotohero.helper.ExtrasHelper;
import com.brayanbedritchuk.zerotohero.helper.ListHelper;
import com.brayanbedritchuk.zerotohero.helper.LogHelper;
import com.brayanbedritchuk.zerotohero.helper.sqlite.ApiLevelHelper;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.model.Workout;
import com.brayanbedritchuk.zerotohero.view.async_tasks.DeleteWorkoutAsyncTask;
import com.brayanbedritchuk.zerotohero.view.async_tasks.LoadWorkoutsAsyncTask;
import com.brayanbedritchuk.zerotohero.view.async_tasks.SaveWorkoutAsyncTask;

import java.util.List;

public class WorkoutListPresenter extends BasePresenter {

    private WorkoutListView view;
    private WorkoutListViewModel viewModel;

    public WorkoutListPresenter(WorkoutListView view) {
        setView(view);
        setViewModel(new WorkoutListViewModel());
    }

    @Override
    protected void onResumeFirstSession() {
        loadWorkouts();
    }

    @Override
    protected void postResume() {
        getView().updateContentViews();
    }

    public void onClickNewWorkout() {
        getView().startNewWorkoutActivity();
    }

    public void onClickWorkout(int position) {
        Workout workout = getWorkouts().get(position);

        if (ApiLevelHelper.isLowerThan(Build.VERSION_CODES.LOLLIPOP)) {
            getView().startWorkoutDetailsActivity(workout);
        } else {
            getView().startWorkoutDetailsActivityWithAnimation(workout);
        }

    }

    public void onResultCanceledWorkoutDetails() {
        loadWorkouts();
    }

    public void onActivityResultOkInsertOrEditWorkout(Intent data) {
        Workout workout = ExtrasHelper.getWorkout(data);
        List<Exercise> exercises = ExtrasHelper.getExercises(data);

        getViewModel().getWorkoutList().add(workout);
        getView().updateContentViews();
        saveWorkout(workout, exercises);
    }

    public void onActivityResultOkWorkoutDetails(Intent data) {
        if (ExtrasHelper.hasWorkoutToDelete(data)) {
            Workout workoutToDelete = ExtrasHelper.getWorkout(data);
            removeWorkoutFromListAndDeleteFromDatabase(workoutToDelete);
        }
    }

    private void removeWorkoutFromListAndDeleteFromDatabase(Workout workoutToDelete) {
        List<Workout> workoutList = getViewModel().getWorkoutList();

        int position = -1;

        for (int i = 0; i < workoutList.size(); i++) {
            if (workoutList.get(i).getId() == workoutToDelete.getId()) {
                position = i;
                break;
            }
        }

        if (position != -1) {
            workoutList.remove(position);
            getView().updateWorkoutRemoved(position);
            deleteWorkout(workoutToDelete);
        } else {
            getView().showToast("Workout not found to delete");
        }
    }

    private void loadWorkouts() {
        Context context = getView().getActivityContext().getApplicationContext();
        new LoadWorkoutsAsyncTask(context, new LoadWorkoutsAsyncTask.Callback() {
            @Override
            public void onSuccess(List<Workout> workoutList) {
                ListHelper.clearAndAdd(workoutList, getViewModel().getWorkoutList());
                getView().updateContentViews();
            }

            @Override
            public void onFail(Exception e) {
                LogHelper.printExceptionLog(e);
            }
        }).execute();
    }

    private void deleteWorkout(Workout workoutToDelete) {
        Context context = getView().getActivityContext().getApplicationContext();
        new DeleteWorkoutAsyncTask(context, workoutToDelete, new DeleteWorkoutAsyncTask.Callback() {
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

    private void saveWorkout(Workout workout, List<Exercise> exercises) {
        Context context = getView().getActivityContext().getApplicationContext();
        new SaveWorkoutAsyncTask(context, workout, exercises, new SaveWorkoutAsyncTask.Callback() {

            @Override
            public void onSuccess() {
            }

            @Override
            public void onFail(Exception e) {
                onSaveWorkoutFail(e);
            }
        }).execute();
    }

    private void onSaveWorkoutFail(Exception e) {
        LogHelper.printExceptionLog(e);
        getView().showToast("");
    }

    public WorkoutListViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(WorkoutListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public WorkoutListView getView() {
        return view;
    }

    public void setView(WorkoutListView view) {
        this.view = view;
    }

    public List<Workout> getWorkouts() {
        return getViewModel().getWorkoutList();
    }

}
