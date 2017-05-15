package br.com.sailboat.zerotohero.view.exercise.details;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.recycler.RecyclerItem;

public class ExerciseDetailsViewModel {

    private long exerciseId = -1;
    private final List<RecyclerItem> exerciseDetails = new ArrayList<>();

    public long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public List<RecyclerItem> getExerciseDetails() {
        return exerciseDetails;
    }
}
