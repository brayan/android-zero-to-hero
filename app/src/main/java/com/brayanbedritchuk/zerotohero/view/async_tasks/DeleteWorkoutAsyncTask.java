package com.brayanbedritchuk.zerotohero.view.async_tasks;

import android.content.Context;

import com.brayanbedritchuk.zerotohero.base.BaseAsyncTask;
import com.brayanbedritchuk.zerotohero.model.Workout;
import com.brayanbedritchuk.zerotohero.persistence.dao.WorkoutDAOSQLite;
import com.brayanbedritchuk.zerotohero.persistence.dao.WorkoutExerciseDAOSQLite;

public class DeleteWorkoutAsyncTask extends BaseAsyncTask {

    private DeleteWorkoutAsyncTask.Callback callback;
    private Context context;
    private Workout workout;

    public DeleteWorkoutAsyncTask(Context context, Workout workout, Callback callback) {
        setContext(context.getApplicationContext());
        setWorkout(workout);
        setCallback(callback);
    }

    protected void onRunningInBackground() throws Exception {
        new WorkoutDAOSQLite(getContext()).delete(getWorkout().getId());
        new WorkoutExerciseDAOSQLite(getContext()).deleteFromWorkout(getWorkout().getId());
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
