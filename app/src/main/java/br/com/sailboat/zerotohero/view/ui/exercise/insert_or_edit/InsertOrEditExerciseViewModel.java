package br.com.sailboat.zerotohero.view.ui.exercise.insert_or_edit;

import br.com.sailboat.zerotohero.base.BaseViewModel;
import br.com.sailboat.zerotohero.model.Exercise;

public class InsertOrEditExerciseViewModel extends BaseViewModel {

    private Exercise exercise;

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
