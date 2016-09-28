package com.brayanbedritchuk.zerotohero.view.workout.details.presenter;

import com.brayanbedritchuk.zerotohero.base.BaseViewModel;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.model.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDetailsViewModel extends BaseViewModel {

    public static final String TAG = WorkoutDetailsViewModel.class.getName();

    private Workout workout;
    private transient List<Exercise> exerciseList = new ArrayList<>();

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
}
