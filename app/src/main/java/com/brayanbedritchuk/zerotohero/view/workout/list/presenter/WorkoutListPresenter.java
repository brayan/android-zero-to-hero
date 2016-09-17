package com.brayanbedritchuk.zerotohero.view.workout.list.presenter;

import android.content.Context;
import android.content.Intent;

import com.brayanbedritchuk.zerotohero.helper.ExtrasHelper;
import com.brayanbedritchuk.zerotohero.helper.ListHelper;
import com.brayanbedritchuk.zerotohero.helper.LogHelper;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.model.Workout;
import com.brayanbedritchuk.zerotohero.view.async_tasks.DeleteWorkoutAsyncTask;
import com.brayanbedritchuk.zerotohero.view.async_tasks.LoadWorkoutsAsyncTask;
import com.brayanbedritchuk.zerotohero.view.async_tasks.SaveWorkoutAsyncTask;

import java.util.List;

public class WorkoutListPresenter {

    private WorkoutListView view;
    private WorkoutListViewModel viewModel;

    public WorkoutListPresenter(WorkoutListView view) {
        setView(view);
        setViewModel(new WorkoutListViewModel());
    }

    public void onResume() {
        verifyAndLoadWorkouts();
        getViewModel().setFirstSession(false);
    }

    public void onClickNewWorkout() {
        getView().startNewWorkoutActivity();
    }

    public void onClickWorkout(int position) {
        Workout workout = getWorkouts().get(position);
        getView().startWorkoutDetailsActivity(workout);
    }

    private void verifyAndLoadWorkouts() {
        if (getViewModel().isFirstSession()) {
            loadWorkouts();
        } else {
            getView().updateContentViews();
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

    public void onActivityResultOkInsertOrEditWorkout(Intent data) {
        Workout workout = ExtrasHelper.getWorkout(data);
        List<Exercise> exercises = ExtrasHelper.getExercises(data);

        getViewModel().getWorkoutList().add(workout);
        // TODO: SORT THE LIST
        getView().updateContentViews();
        saveWorkout(workout, exercises);
    }

    public void onActivityResultOkWorkoutDetails(Intent data) {
        if (ExtrasHelper.hasWorkoutToDelete(data)) {
            Workout workoutToDelete = ExtrasHelper.getWorkout(data);

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
    }

    private void deleteWorkout(Workout workoutToDelete) {
        Context context = getView().getActivityContext().getApplicationContext();
        new DeleteWorkoutAsyncTask(context, workoutToDelete, new DeleteWorkoutAsyncTask.Callback() {
            @Override
            public void onSuccess() {
                // TODO
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

    public void onResultCanceledWorkoutDetails() {
        loadWorkouts();
    }



    //    public WorkoutDAO getWorkoutDAO() {
    //        return WorkoutDAOSQLite.getInstance(getView().getActivityContext());
    //    }

}
