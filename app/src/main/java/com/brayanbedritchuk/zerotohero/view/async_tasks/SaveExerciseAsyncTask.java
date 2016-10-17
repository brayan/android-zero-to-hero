package com.brayanbedritchuk.zerotohero.view.async_tasks;

import android.content.Context;

import com.brayanbedritchuk.zerotohero.base.BaseAsyncTask;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.persistence.dao.ExerciseDAOSQLite;

public class SaveExerciseAsyncTask extends BaseAsyncTask {

    private Exercise exercise;
    private Context context;

    SaveExerciseAsyncTask.Callback callback;

    public SaveExerciseAsyncTask(Context context, Exercise exercise, Callback callback) {
        setContext(context.getApplicationContext());
        setExercise(exercise);
        setCallback(callback);
    }

    @Override
    protected void onRunningInBackground() throws Exception {
        if (isExerciseNew()) {
            saveNewExercise();
        } else {
            updateExercise();
        }
    }

    @Override
    protected void onSuccess() {
        getCallback().onSuccess();
    }

    @Override
    protected void onFail(Exception e) {
        getCallback().onFail(e);
    }

    private void saveNewExercise() {
        long exerciseId = new ExerciseDAOSQLite(getContext()).saveAndGetId(getExercise());
        getExercise().setId(exerciseId);
    }

    private void updateExercise() {
        new ExerciseDAOSQLite(getContext()).update(getExercise());
    }

    private boolean isExerciseNew() {
        return getExercise().getId() == -1;
    }



    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }



    public interface Callback {
        void onSuccess();
        void onFail(Exception e);
    }
}
