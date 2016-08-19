package com.brayanbedritchuk.zerotohero.model.viewmodel;

import com.brayanbedritchuk.zerotohero.model.entity.Workout;

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
