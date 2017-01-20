package br.com.sailboat.zerotohero.view.ui.exercise.selector;

import android.util.LongSparseArray;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.base.BaseViewModel;
import br.com.sailboat.zerotohero.model.Exercise;

public class ExerciseChooserViewModel extends BaseViewModel {

    private final LongSparseArray<Exercise> selectedExercises;
    private final List<Exercise> exerciseList;

    public ExerciseChooserViewModel() {
        this.exerciseList = new ArrayList<>();
        this.selectedExercises = new LongSparseArray<>();
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public LongSparseArray<Exercise> getSelectedExercises() {
        return selectedExercises;
    }
}
