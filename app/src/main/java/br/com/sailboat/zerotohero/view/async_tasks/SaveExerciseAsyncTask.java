package br.com.sailboat.zerotohero.view.async_tasks;

import android.content.Context;

import br.com.sailboat.zerotohero.base.BaseAsyncTask;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;

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
    protected void onDoInBackground() throws Exception {
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

    private void saveNewExercise() throws Exception {
        long exerciseId = new ExerciseSQLite(getContext()).saveAndGetId(getExercise());
        getExercise().setId(exerciseId);
    }

    private void updateExercise() throws Exception {
        new ExerciseSQLite(getContext()).update(getExercise());
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
