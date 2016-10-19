package br.com.sailboat.zerotohero.view.async_tasks;

import android.content.Context;

import br.com.sailboat.zerotohero.base.BaseAsyncTask;
import br.com.sailboat.zerotohero.model.Workout;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutExerciseSQLite;

public class DeleteWorkoutAsyncTask extends BaseAsyncTask {

    private DeleteWorkoutAsyncTask.Callback callback;
    private Context context;
    private Workout workout;

    public DeleteWorkoutAsyncTask(Context context, Workout workout, Callback callback) {
        setContext(context.getApplicationContext());
        setWorkout(workout);
        setCallback(callback);
    }

    protected void onDoInBackground() throws Exception {
        new WorkoutSQLite(getContext()).delete(getWorkout().getId());
        new WorkoutExerciseSQLite(getContext()).deleteFromWorkout(getWorkout().getId());
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

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }



    public interface Callback {
        void onSuccess();
        void onFail(Exception e);
    }

}
