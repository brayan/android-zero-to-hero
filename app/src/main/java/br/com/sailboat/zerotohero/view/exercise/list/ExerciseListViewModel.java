package br.com.sailboat.zerotohero.view.exercise.list;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.model.view.ExerciseView;

public class ExerciseListViewModel {

    private final List<ExerciseView> exercises;

    public ExerciseListViewModel() {
        this.exercises = new ArrayList<>();
    }

    public List<ExerciseView> getExercises() {
        return exercises;
    }
}
