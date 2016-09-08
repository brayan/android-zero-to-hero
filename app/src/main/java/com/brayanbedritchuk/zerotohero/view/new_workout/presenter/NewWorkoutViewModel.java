package com.brayanbedritchuk.zerotohero.view.new_workout.presenter;

import com.brayanbedritchuk.zerotohero.model.Workout;

import java.util.ArrayList;
import java.util.List;

public class NewWorkoutViewModel {

    private boolean firstSession = true;
    private final List<Workout> workoutList;

    public NewWorkoutViewModel() {
        this.workoutList = new ArrayList<>();
    }

    public boolean isFirstSession() {
        return firstSession;
    }

    public void setFirstSession(boolean firstSession) {
        this.firstSession = firstSession;
    }

    public List<Workout> getWorkoutList() {
        return workoutList;
    }
}
