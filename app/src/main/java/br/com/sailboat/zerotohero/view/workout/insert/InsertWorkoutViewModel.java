package br.com.sailboat.zerotohero.view.workout.insert;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.helper.EntityHelper;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;

public class InsertWorkoutViewModel {

    private long workoutId = EntityHelper.NO_ID;
    private String name;
    private final List<Exercise> exercises;

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

    public List<Exercise> getExercises() {
        return exercises;
    }

}
