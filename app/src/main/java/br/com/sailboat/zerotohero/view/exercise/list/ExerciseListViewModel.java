package br.com.sailboat.zerotohero.view.exercise.list;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.filter.Filter;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;

public class ExerciseListViewModel {

    private Filter filter;
    private final List<Exercise> exercises;

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public ExerciseListViewModel() {
        this.exercises = new ArrayList<>();
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

}
