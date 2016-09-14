package com.brayanbedritchuk.zerotohero.view.insert_or_edit_workout.presenter;

import com.brayanbedritchuk.zerotohero.base.BaseViewModel;
import com.brayanbedritchuk.zerotohero.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class InsertOrEditWorkoutViewModel extends BaseViewModel {

    private final List<Exercise> selectedExercises;

    public InsertOrEditWorkoutViewModel() {
        this.selectedExercises = new ArrayList<>();
    }

    public List<Exercise> getSelectedExercises() {
        return selectedExercises;
    }
}
