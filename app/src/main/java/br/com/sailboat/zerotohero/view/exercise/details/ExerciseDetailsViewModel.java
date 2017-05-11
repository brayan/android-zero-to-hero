package br.com.sailboat.zerotohero.view.exercise.details;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.model.sqlite.ExerciseHistory;

public class ExerciseDetailsViewModel {

    private long exerciseId = -1;
    private Exercise exercise;
    private final List<ExerciseHistory> exerciseHistoryList = new ArrayList<>();

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

    public List<ExerciseHistory> getExerciseHistoryList() {
        return exerciseHistoryList;
    }
}
