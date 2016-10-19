package br.com.sailboat.zerotohero.view.async_tasks;

import android.content.Context;

import br.com.sailboat.zerotohero.base.BaseAsyncTask;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutExerciseSQLite;

public class DeleteExerciseAsyncTask extends BaseAsyncTask {

    private DeleteExerciseAsyncTask.Callback callback;
    private Context context;
    private Exercise exercise;

    public DeleteExerciseAsyncTask(Context context, Exercise exercise, Callback callback) {
        setContext(context.getApplicationContext());
        setExercise(exercise);
        setCallback(callback);
    }

    protected void onDoInBackground() throws Exception {
        new ExerciseSQLite(getContext()).delete(getExercise().getId());
        new WorkoutExerciseSQLite(getContext()).deleteFromExercise(getExercise().getId());
    }

    @Override
    protected void onSuccess() {
        getCallback().onSuccess();
    }

    @Override
    protected void onFail(Exception e) {
        getCallback().onFail(e);
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }



    public interface Callback {
        void onSuccess();
        void onFail(Exception e);
    }

}
