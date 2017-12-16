package br.com.sailboat.zerotohero.view.exercise.selector;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import br.com.sailboat.canoe.filter.Filter;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;

public class ExerciseSelectorViewModel {

    private Filter filter = new Filter();
    private final LinkedHashMap<Long, Exercise> selectedExercises = new LinkedHashMap<>();
    private final List<Exercise> exerciseList = new ArrayList<>();

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public LinkedHashMap<Long, Exercise> getSelectedExercises() {
        return selectedExercises;
    }

}
