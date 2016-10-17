package com.brayanbedritchuk.zerotohero.view.async_tasks;

import android.content.Context;

import com.brayanbedritchuk.zerotohero.base.BaseAsyncTask;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.persistence.dao.ExerciseDAOSQLite;

import java.util.ArrayList;
import java.util.List;

public class LoadExercisesAsyncTask extends BaseAsyncTask {

    private List<Exercise> exerciseList;
    private Context context;
    private LoadExercisesAsyncTask.Callback callback;

    public LoadExercisesAsyncTask(Context context, Callback callback) {
        setContext(context.getApplicationContext());
        setCallback(callback);
        setExerciseList(new ArrayList<Exercise>());
    }

    @Override
    protected void onRunningInBackground() throws Exception {
        setExerciseList(new ExerciseDAOSQLite(getContext()).getAll());
    }

    @Override
    protected void onSuccess() {
        getCallback().onSuccess(getExerciseList());
    }

    @Override
    protected void onFail(Exception e) {
        getCallback().onFail(e);
    }



    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
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
        void onSuccess(List<Exercise> exercises);
        void onFail(Exception e);
    }

}
