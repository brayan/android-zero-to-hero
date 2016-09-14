package com.brayanbedritchuk.zerotohero.view.exercise_list.presenter;

import com.brayanbedritchuk.zerotohero.base.BaseViewModel;
import com.brayanbedritchuk.zerotohero.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseListViewModel extends BaseViewModel {

    private final List<Exercise> exercises;

    public ExerciseListViewModel() {
        this.exercises = new ArrayList<>();
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
