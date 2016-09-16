package com.brayanbedritchuk.zerotohero.view.workout.insert_or_edit.presenter;

import android.support.annotation.StringRes;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.base.BaseAsyncTask;
import com.brayanbedritchuk.zerotohero.helper.LogHelper;
import com.brayanbedritchuk.zerotohero.helper.StringHelper;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.model.Workout;
import com.brayanbedritchuk.zerotohero.exception.RequiredComponentException;
import com.brayanbedritchuk.zerotohero.persistence.dao.WorkoutDAOSQLite;
import com.brayanbedritchuk.zerotohero.persistence.dao.WorkoutExerciseDAOSQLite;

public class SaveWorkoutAsyncTask extends BaseAsyncTask {

    private InsertOrEditWorkoutView view;
    private InsertOrEditWorkoutViewModel viewModel;

    private String workoutName;

    public SaveWorkoutAsyncTask(InsertOrEditWorkoutView view, InsertOrEditWorkoutViewModel viewModel) {
        setView(view);
        setViewModel(viewModel);
    }

    @Override
    protected void onPreExecute() {
        setWorkoutName(getView().getTextFromWorkoutName());
    }

    @Override
    protected void onRunningInBackground() throws Exception {
        checkRequiredComponents();
        buildAndSaveEntities();
    }

    @Override
    protected void onSuccess() {
        getView().closeActivity();
    }

    @Override
    protected void onFail(Exception e) {
        LogHelper.printExceptionLog(e);
        getView().showDialog(e.getMessage());
    }

    private void checkRequiredComponents() throws Exception {
        if (StringHelper.isEmpty(getWorkoutName())) {
            throw new RequiredComponentException(getString(R.string.exeption_workout_name));
        }

        if (getViewModel().getSelectedExercises().size() == 0) {
            throw new RequiredComponentException(getString(R.string.exeption_selected_exercises));
        }
    }

    private void buildAndSaveEntities() throws Exception {
        Workout workout = new Workout();
        workout.setName(getWorkoutName());

        long workoutId = new WorkoutDAOSQLite(getView().getActivityContext()).saveAndGetId(workout);
        workout.setId(workoutId);

        WorkoutExerciseDAOSQLite dao = new WorkoutExerciseDAOSQLite(getView().getActivityContext());

        for (Exercise exercise : getViewModel().getSelectedExercises()) {
            dao.save(workoutId, exercise.getId());
        }

    }

    private String getString(@StringRes int id) {
        return getView().getActivityContext().getString(id);
    }

    public InsertOrEditWorkoutView getView() {
        return view;
    }

    public void setView(InsertOrEditWorkoutView view) {
        this.view = view;
    }

    public InsertOrEditWorkoutViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(InsertOrEditWorkoutViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }
}
