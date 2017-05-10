package br.com.sailboat.zerotohero.view.workout.details;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.model.sqlite.Workout;
import br.com.sailboat.zerotohero.model.view.ExerciseView;

public class WorkoutDetailsViewModel {

    private long workoutId = -1;
    private Workout workout;
    private final List<ExerciseView> exerciseList = new ArrayList<>();

    public long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public List<ExerciseView> getExerciseList() {
        return exerciseList;
    }

}
