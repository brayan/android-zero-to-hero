package com.brayanbedritchuk.zerotohero.view.exercise.details.presenter;

import com.brayanbedritchuk.zerotohero.base.BaseViewModel;
import com.brayanbedritchuk.zerotohero.model.Exercise;

public class ExerciseDetailsViewModel extends BaseViewModel {

    private Exercise exercise;

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
