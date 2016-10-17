package br.com.sailboat.zerotohero.view.exercise.chooser.presenter;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.base.BaseViewModel;
import br.com.sailboat.zerotohero.model.Exercise;

public class ExerciseChooserViewModel extends BaseViewModel {

    private final SparseArray<Exercise> selectedExercises;
    private final List<Exercise> exerciseList;

    public ExerciseChooserViewModel() {
        this.exerciseList = new ArrayList<>();
        this.selectedExercises = new SparseArray<>();
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public SparseArray<Exercise> getSelectedExercises() {
        return selectedExercises;
    }
}
