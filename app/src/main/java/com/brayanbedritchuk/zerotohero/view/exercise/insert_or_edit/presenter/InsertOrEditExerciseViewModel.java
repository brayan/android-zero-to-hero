package com.brayanbedritchuk.zerotohero.view.exercise.insert_or_edit.presenter;

import com.brayanbedritchuk.zerotohero.base.BaseViewModel;
import com.brayanbedritchuk.zerotohero.model.Exercise;

public class InsertOrEditExerciseViewModel extends BaseViewModel {

    private Exercise exercise;

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
