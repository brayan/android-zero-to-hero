package com.brayanbedritchuk.zerotohero.view.workout_list.presenter;

import com.brayanbedritchuk.zerotohero.model.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListViewModel {

    private boolean firstSession = true;
    private final List<Workout> workoutList;

    public WorkoutListViewModel() {
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
