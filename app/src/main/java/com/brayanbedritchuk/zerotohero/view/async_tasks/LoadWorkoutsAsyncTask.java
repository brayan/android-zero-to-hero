package com.brayanbedritchuk.zerotohero.view.async_tasks;

import android.content.Context;

import com.brayanbedritchuk.zerotohero.base.BaseAsyncTask;
import com.brayanbedritchuk.zerotohero.model.Workout;
import com.brayanbedritchuk.zerotohero.persistence.dao.WorkoutDAOSQLite;

import java.util.ArrayList;
import java.util.List;

public class LoadWorkoutsAsyncTask extends BaseAsyncTask {

    private List<Workout> workoutList;
    private Context context;

    private LoadWorkoutsAsyncTask.Callback callback;

    public LoadWorkoutsAsyncTask(Context context, Callback callback) {
        setContext(context);
        setWorkoutList(new ArrayList<Workout>());
        setCallback(callback);
    }

    @Override
    protected void onRunningInBackground() throws Exception {
        setWorkoutList(new WorkoutDAOSQLite(getContext()).getAll());
    }

    @Override
    protected void onSuccess() {
        getCallback().onSuccess(getWorkoutList());
    }

    @Override
    protected void onFail(Exception e) {
        getCallback().onFail(e);
    }

    public List<Workout> getWorkoutList() {
        return workoutList;
    }

    public void setWorkoutList(List<Workout> workoutList) {
        this.workoutList = workoutList;
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

    public interface Callback {

        void onSuccess(List<Workout> workoutList);
        void onFail(Exception e);

    }
}
