package br.com.sailboat.zerotohero.view.exercise.selector;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import br.com.sailboat.zerotohero.model.view.ExerciseView;

public class ExerciseSelectorViewModel {

    private final LinkedHashMap<Long, ExerciseView> selectedExercises;
    private final List<ExerciseView> exerciseList;

    public ExerciseSelectorViewModel() {
        this.exerciseList = new ArrayList<>();
        this.selectedExercises = new LinkedHashMap<>();
    }

    public List<ExerciseView> getExerciseList() {
        return exerciseList;
    }

    public LinkedHashMap<Long, ExerciseView> getSelectedExercises() {
        return selectedExercises;
    }

}
