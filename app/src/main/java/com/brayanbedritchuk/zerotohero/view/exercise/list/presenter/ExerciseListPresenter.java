package com.brayanbedritchuk.zerotohero.view.exercise.list.presenter;

import android.content.Context;
import android.content.Intent;

import com.brayanbedritchuk.zerotohero.base.BasePresenter;
import com.brayanbedritchuk.zerotohero.helper.ExtrasHelper;
import com.brayanbedritchuk.zerotohero.helper.ListHelper;
import com.brayanbedritchuk.zerotohero.helper.LogHelper;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.view.async_tasks.LoadExercisesAsyncTask;
import com.brayanbedritchuk.zerotohero.view.async_tasks.SaveExerciseAsyncTask;

import java.util.List;

public class ExerciseListPresenter extends BasePresenter {

    private ExerciseListView view;
    private ExerciseListViewModel viewModel;

    public ExerciseListPresenter(ExerciseListView view) {
        setView(view);
        setViewModel(new ExerciseListViewModel());
    }

    @Override
    protected void onResumeFirstSession() {
        loadExercises();
    }

    @Override
    protected void postResume() {
        getView().updateContentViews();
    }

    public void onClickExercise(int position) {
        Exercise exercise = getExercises().get(position);
        getView().startExerciseDetailsActivity(exercise);
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

    public void onClickNewExercise() {
        getView().startNewExerciseActivity();
    }

    public void onActivityResultOkInsertOrEditExercise(Intent data) {
        Exercise exercise = ExtrasHelper.getExercise(data);

        getViewModel().getExercises().add(exercise);
        getView().updateContentViews();
        saveExercise(exercise);
    }

    private void saveExercise(Exercise exercise) {
        Context context = getView().getActivityContext().getApplicationContext();
        new SaveExerciseAsyncTask(context, exercise, new SaveExerciseAsyncTask.Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onFail(Exception e) {
                onSaveExerciseFail(e);
            }
        }).execute();
    }

    private void onSaveExerciseFail(Exception e) {
        LogHelper.printExceptionLog(e);
        getView().showDialog(e.getMessage());
    }

}
