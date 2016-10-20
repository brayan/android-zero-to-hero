package br.com.sailboat.zerotohero.view.ui.exercise.list.view_model;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.base.BaseViewModel;
import br.com.sailboat.zerotohero.model.Exercise;

public class ExerciseListViewModel extends BaseViewModel {

    private final List<Exercise> exercises;

    public ExerciseListViewModel() {
        this.exercises = new ArrayList<>();
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
