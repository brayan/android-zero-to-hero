package com.brayanbedritchuk.zerotohero.view.workout.insert_or_edit.presenter;

import com.brayanbedritchuk.zerotohero.base.BaseViewModel;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.model.Workout;

import java.util.ArrayList;
import java.util.List;

public class InsertOrEditWorkoutViewModel extends BaseViewModel {

    private final List<Exercise> exercises;
    private Workout workout;

    public InsertOrEditWorkoutViewModel() {
        this.exercises = new ArrayList<>();
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}
