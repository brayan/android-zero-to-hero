package com.brayanbedritchuk.zerotohero.view.workout.insert_or_edit.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.base.BasePresenter;
import com.brayanbedritchuk.zerotohero.exception.RequiredComponentException;
import com.brayanbedritchuk.zerotohero.helper.ExtrasHelper;
import com.brayanbedritchuk.zerotohero.helper.ListHelper;
import com.brayanbedritchuk.zerotohero.helper.LogHelper;
import com.brayanbedritchuk.zerotohero.helper.StringHelper;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.model.Workout;
import com.brayanbedritchuk.zerotohero.view.async_tasks.LoadExercisesFromWorkoutAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class InsertOrEditWorkoutPresenter extends BasePresenter {

    private InsertOrEditWorkoutView view;
    private InsertOrEditWorkoutViewModel viewModel;

    public InsertOrEditWorkoutPresenter(InsertOrEditWorkoutView view) {
        setView(view);
        setViewModel(new InsertOrEditWorkoutViewModel());
    }

    @Override
    protected void onResumeFirstSession() {
        if (hasWorkoutToEdit()) {
            getView().updateWorkoutNameView(getViewModel().getWorkout().getName());
            getView().hideKeyboard();
            loadExercises();
        }
    }

    @Override
    protected void postResume() {
        updateContentViews();
    }

    public void onClickAddExercises() {
        ArrayList array = (ArrayList) getViewModel().getExercises();
        getView().startExercisesChooserActivity(array);
    }

    public void onResultOkExerciseChooser(Intent data) {
        List<Exercise> exercises = ExtrasHelper.getExercises(data);
        ListHelper.clearAndAdd(exercises, getViewModel().getExercises());
        getView().updateExercisesListAndVisibility();
    }

    public void onClickMenuSave() {
        try {
            checkRequiredComponents();
            buildAndReturnEntities();
        } catch (Exception e) {
            getView().showDialog(e.getMessage());
        }
    }

    public void onReceiveWorkout(Workout workout) {
        getViewModel().setWorkout(workout);
    }

    private void loadExercises() {
        Context context = getView().getActivityContext().getApplicationContext();
        long workoutId = getViewModel().getWorkout().getId();
        new LoadExercisesFromWorkoutAsyncTask(context, workoutId, new LoadExercisesFromWorkoutAsyncTask.Callback() {
            @Override
            public void onSuccess(List<Exercise> exercises) {
                ListHelper.clearAndAdd(exercises, getViewModel().getExercises());
                getView().updateExercisesListAndVisibility();
            }

            @Override
            public void onFail(Exception e) {
                LogHelper.printExceptionLog(e);
                getView().showToast(e.getMessage());
            }
        }).execute();
    }

    private void updateContentViews() {
        updateToolbarTitle();
        getView().updateExercisesListAndVisibility();
    }

    private void updateToolbarTitle() {
        String title = null;

        if (hasWorkoutToEdit()) {
            title = getString(R.string.edit_workout);
        } else {
            title = getString(R.string.new_workout);
        }

        getView().updateToolbarTitle(title);
    }

    private boolean hasWorkoutToEdit() {
        return getViewModel().getWorkout() != null;
    }

    public InsertOrEditWorkoutViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(InsertOrEditWorkoutViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public InsertOrEditWorkoutView getView() {
        return view;
    }

    public void setView(InsertOrEditWorkoutView view) {
        this.view = view;
    }

    public List<Exercise> getExercises() {
        return getViewModel().getExercises();
    }

    private void buildAndReturnEntities() {
        Workout workout = getViewModel().getWorkout();

        if (workout == null) {
            workout = new Workout();
            workout.setId(-1);
        }

        workout.setName(getView().getTextFromWorkoutName());

        getView().closeActivityWithResultOk(workout, getViewModel().getExercises());
    }

    private void checkRequiredComponents() throws Exception {
        String name = getView().getTextFromWorkoutName();

        if (StringHelper.isEmpty(name)) {
            throw new RequiredComponentException(getString(R.string.exeption_workout_name));
        }

        if (getViewModel().getExercises().size() == 0) {
            throw new RequiredComponentException(getString(R.string.exeption_selected_exercises));
        }
    }

    private String getString(@StringRes int id) {
        return getView().getActivityContext().getString(id);
    }
}
