package br.com.sailboat.zerotohero.model.sqlite;

import br.com.sailboat.canoe.recycler.RecyclerItem;
import br.com.sailboat.zerotohero.helper.ViewType;

public class ExerciseHistory implements RecyclerItem {

    private long id = -1;
    private long exerciseId;
    private double weight;
    private int sets;
    private int reps;
    private String lastModified;

    @Override
    public int getViewType() {
        return ViewType.EXERCISE_HISTORY;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ExerciseHistory)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        ExerciseHistory history = (ExerciseHistory) obj;

        if (history.getExerciseId() == exerciseId
                && history.getWeight() == weight
                && history.getSets() == sets
                && history.getReps() == reps) {
            return true;
        }

        return false;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

}
