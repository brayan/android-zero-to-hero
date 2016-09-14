package com.brayanbedritchuk.zerotohero.view.exercise.chooser.presenter;

import android.util.SparseArray;

import com.brayanbedritchuk.zerotohero.base.BaseViewModel;
import com.brayanbedritchuk.zerotohero.model.Exercise;

import java.util.ArrayList;
import java.util.List;

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
