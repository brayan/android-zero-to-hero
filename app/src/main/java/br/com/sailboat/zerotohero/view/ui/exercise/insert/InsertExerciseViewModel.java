package br.com.sailboat.zerotohero.view.ui.exercise.insert;

import br.com.sailboat.zerotohero.base.BaseViewModel;

public class InsertExerciseViewModel extends BaseViewModel {

    private long exerciseId = -1;
    private String name;
    private double weight;
    private int set;
    private int repetition;

    public long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

}
