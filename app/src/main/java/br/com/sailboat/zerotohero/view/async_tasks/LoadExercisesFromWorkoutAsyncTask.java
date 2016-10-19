package br.com.sailboat.zerotohero.view.async_tasks;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.base.BaseAsyncTask;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.persistence.dao.ExerciseDAOSQLite;

public class LoadExercisesFromWorkoutAsyncTask extends BaseAsyncTask {

    private List<Exercise> exerciseList;
    private Context context;
    private long workoutId;
    private LoadExercisesFromWorkoutAsyncTask.Callback callback;

    public LoadExercisesFromWorkoutAsyncTask(Context context, long workoutId, Callback callback) {
        setContext(context.getApplicationContext());
        setWorkoutId(workoutId);
        setCallback(callback);
        setExerciseList(new ArrayList<Exercise>());
    }

    @Override
    protected void onDoInBackground() throws Exception {
        setExerciseList(new ExerciseDAOSQLite(getContext()).getFromWorkout(getWorkoutId()));
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

    public long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
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
