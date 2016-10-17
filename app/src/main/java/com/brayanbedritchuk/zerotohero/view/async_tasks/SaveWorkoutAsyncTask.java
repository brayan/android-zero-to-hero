package com.brayanbedritchuk.zerotohero.view.async_tasks;

import android.content.Context;

import com.brayanbedritchuk.zerotohero.base.BaseAsyncTask;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.model.Workout;
import com.brayanbedritchuk.zerotohero.persistence.dao.WorkoutDAOSQLite;
import com.brayanbedritchuk.zerotohero.persistence.dao.WorkoutExerciseDAOSQLite;

import java.util.List;

public class SaveWorkoutAsyncTask extends BaseAsyncTask {

    private Workout workout;
    private List<Exercise> exercises;
    private Context context;

    private SaveWorkoutAsyncTask.Callback callback;

    public SaveWorkoutAsyncTask(Context context, Workout workout, List<Exercise> exercises, Callback callback) {
        setContext(context.getApplicationContext());
        setWorkout(workout);
        setExercises(exercises);
        setCallback(callback);
    }

    @Override
    protected void onRunningInBackground() throws Exception {
        saveEntities();
    }

    @Override
    protected void onSuccess() {
        getCallback().onSuccess();
    }

    @Override
    protected void onFail(Exception e) {
        getCallback().onFail(e);
    }

    private void saveEntities() throws Exception {
        if (isWorkoutNew()) {
            saveNewWorkout();
        } else {
            updateWorkout();
            deleteRelationshipWorkoutExercise();
        }
        saveRalationshipWorkoutExercise();
    }

    private void updateWorkout() throws Exception {
        new WorkoutDAOSQLite(getContext()).update(getWorkout());
    }

    private void saveNewWorkout() throws Exception {
        long workoutId = new WorkoutDAOSQLite(getContext()).saveAndGetId(getWorkout());
        getWorkout().setId(workoutId);
    }

    private void deleteRelationshipWorkoutExercise() throws Exception {
        WorkoutExerciseDAOSQLite dao = new WorkoutExerciseDAOSQLite(getContext());

        dao.deleteFromWorkout(getWorkout().getId());
    }

    private void saveRalationshipWorkoutExercise() throws Exception {
        WorkoutExerciseDAOSQLite dao = new WorkoutExerciseDAOSQLite(getContext());
        for (Exercise exercise : getExercises()) {
            dao.save(getWorkout().getId(), exercise.getId());
        }
    }

    private boolean isWorkoutNew() {
        return getWorkout().getId() == -1;
    }



    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
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
        void onSuccess();
        void onFail(Exception e);
    }

}
