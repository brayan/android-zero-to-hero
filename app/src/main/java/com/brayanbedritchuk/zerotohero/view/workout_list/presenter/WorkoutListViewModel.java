package com.brayanbedritchuk.zerotohero.view.workout_list.presenter;

import com.brayanbedritchuk.zerotohero.base.BaseViewModel;
import com.brayanbedritchuk.zerotohero.model.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListViewModel extends BaseViewModel {

    private final List<Workout> workoutList;

    public WorkoutListViewModel() {
        this.workoutList = new ArrayList<>();
    }

    public List<Workout> getWorkoutList() {
        return workoutList;
    }
}
