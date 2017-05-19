package br.com.sailboat.zerotohero.view.exercise.selector;

import android.util.LongSparseArray;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.model.view.ExerciseView;

public class ExerciseSelectorViewModel {

    private final LongSparseArray<ExerciseView> selectedExercises;
    private final List<ExerciseView> exerciseList;

    public ExerciseSelectorViewModel() {
        this.exerciseList = new ArrayList<>();
        this.selectedExercises = new LongSparseArray<>();
    }

    public List<ExerciseView> getExerciseList() {
        return exerciseList;
    }

    public LongSparseArray<ExerciseView> getSelectedExercises() {
        return selectedExercises;
    }

}
