package com.brayanbedritchuk.zerotohero.workout_list.presenter;

import com.brayanbedritchuk.zerotohero.model.Workout;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel {

    private boolean firstSession = true;
    private final List<Workout> workoutList;

    public MainViewModel() {
        this.workoutList = new ArrayList<>();
    }

    public List<Workout> getWorkoutList() {
        return this.workoutList;
    }

    public boolean isFirstSession() {
        return firstSession;
    }

    public void setFirstSession(boolean firstSession) {
        this.firstSession = firstSession;
    }
}
