package com.brayanbedritchuk.zerotohero.view.exercise.list.presenter;

import android.content.Context;

import com.brayanbedritchuk.zerotohero.helper.ListHelper;
import com.brayanbedritchuk.zerotohero.helper.LogHelper;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.view.async_tasks.LoadExercisesAsyncTask;

import java.util.List;

public class ExerciseListPresenter {

    private ExerciseListView view;
    private ExerciseListViewModel viewModel;

    public ExerciseListPresenter(ExerciseListView view) {
        setView(view);
        setViewModel(new ExerciseListViewModel());
    }

    public void onResume() {
        verifyAndLoadData();
        getViewModel().setFirstSession(false);
    }

    public void onClickExercise(int position) {
        Exercise exercise = getExercises().get(position);
        getView().startExerciseDetailsActivity(exercise);
    }

    private void verifyAndLoadData() {
        if (getViewModel().isFirstSession()) {
            loadExercises();
        } else {
            getView().updateContentViews();
        }
    }

    private void loadExercises() {
        Context context = getView().getActivityContext().getApplicationContext();
        new LoadExercisesAsyncTask(context, new LoadExercisesAsyncTask.Callback() {
            @Override
            public void onSuccess(List<Exercise> exercises) {
                ListHelper.clearAndAdd(exercises, getViewModel().getExercises());
                getView().updateContentViews();
            }

            @Override
            public void onFail(Exception e) {
                LogHelper.printExceptionLog(e);
                getView().showToast(e.getMessage());
            }
        }).execute();
    }

    public ExerciseListViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(ExerciseListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public ExerciseListView getView() {
        return view;
    }

    public void setView(ExerciseListView view) {
        this.view = view;
    }

    public List<Exercise> getExercises() {
        return getViewModel().getExercises();
    }

//    public WorkoutDAO getWorkoutDAO() {
//        return WorkoutDAOSQLite.getInstance(getView().getActivityContext());
//    }

}
