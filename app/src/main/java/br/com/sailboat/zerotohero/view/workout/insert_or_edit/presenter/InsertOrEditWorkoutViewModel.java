package br.com.sailboat.zerotohero.view.workout.insert_or_edit.presenter;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.base.BaseViewModel;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.model.Workout;

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
