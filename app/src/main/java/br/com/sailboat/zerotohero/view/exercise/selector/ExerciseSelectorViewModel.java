package br.com.sailboat.zerotohero.view.exercise.selector;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import br.com.sailboat.zerotohero.model.sqlite.Exercise;

public class ExerciseSelectorViewModel {

    private final LinkedHashMap<Long, Exercise> selectedExercises;
    private final List<Exercise> exerciseList;

    public ExerciseSelectorViewModel() {
        this.exerciseList = new ArrayList<>();
        this.selectedExercises = new LinkedHashMap<>();
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public LinkedHashMap<Long, Exercise> getSelectedExercises() {
        return selectedExercises;
    }

}
