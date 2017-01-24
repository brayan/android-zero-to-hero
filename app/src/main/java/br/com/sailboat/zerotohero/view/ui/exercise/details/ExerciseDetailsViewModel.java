package br.com.sailboat.zerotohero.view.ui.exercise.details;

import br.com.sailboat.zerotohero.base.BaseViewModel;
import br.com.sailboat.zerotohero.model.Exercise;

public class ExerciseDetailsViewModel extends BaseViewModel {

    private long exerciseId = -1;
    private Exercise exercise;

    public long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
