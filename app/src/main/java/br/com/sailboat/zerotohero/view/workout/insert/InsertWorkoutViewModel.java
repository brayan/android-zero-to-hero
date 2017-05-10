package br.com.sailboat.zerotohero.view.workout.insert;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.model.view.ExerciseView;

public class InsertWorkoutViewModel {

    private long workoutId = -1;
    private String name;
    private final List<ExerciseView> exercises;

    public long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InsertWorkoutViewModel() {
        this.exercises = new ArrayList<>();
    }

    public List<ExerciseView> getExercises() {
        return exercises;
    }

}
