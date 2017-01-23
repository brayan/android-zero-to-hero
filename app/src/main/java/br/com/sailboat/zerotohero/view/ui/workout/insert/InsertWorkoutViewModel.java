package br.com.sailboat.zerotohero.view.ui.workout.insert;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.base.BaseViewModel;
import br.com.sailboat.zerotohero.model.Exercise;

public class InsertWorkoutViewModel extends BaseViewModel {

    private long workoutId = -1;
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
