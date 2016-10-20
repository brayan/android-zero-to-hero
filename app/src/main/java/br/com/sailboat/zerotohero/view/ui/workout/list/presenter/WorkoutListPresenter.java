package br.com.sailboat.zerotohero.view.ui.workout.list.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.List;

import br.com.sailboat.zerotohero.base.BasePresenter;
import br.com.sailboat.zerotohero.helper.ApiLevelHelper;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.helper.ListHelper;
import br.com.sailboat.zerotohero.helper.LogHelper;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.model.Workout;
import br.com.sailboat.zerotohero.view.async_tasks.DeleteWorkoutAsyncTask;
import br.com.sailboat.zerotohero.view.async_tasks.LoadWorkoutsAsyncTask;
import br.com.sailboat.zerotohero.view.async_tasks.SaveWorkoutAsyncTask;
import br.com.sailboat.zerotohero.view.ui.workout.list.view_model.WorkoutListViewModel;

public class WorkoutListPresenter extends BasePresenter {

    private View view;
    private WorkoutListViewModel viewModel;

    public WorkoutListPresenter(View view) {
        setView(view);
        setViewModel(new WorkoutListViewModel());
    }

    @Override
    protected void onResumeFirstSession() {
        loadWorkouts();
    }

    @Override
    protected void onResumeAfterRestart() {
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
        new LoadWorkoutsAsyncTask(getContext(), new LoadWorkoutsAsyncTask.Callback() {

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
        new DeleteWorkoutAsyncTask(getContext(), workoutToDelete, new DeleteWorkoutAsyncTask.Callback() {
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

    private Context getContext() {
        return getView().getActivityContext();
    }

    public WorkoutListViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(WorkoutListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<Workout> getWorkouts() {
        return getViewModel().getWorkoutList();
    }



    public interface View {
        Context getActivityContext();
        void updateContentViews();
        void showToast(String message);
        void startNewWorkoutActivity();
        void startWorkoutDetailsActivity(Workout workout);
        void updateWorkoutRemoved(int position);
        void startWorkoutDetailsActivityWithAnimation(Workout workout);
    }

}
