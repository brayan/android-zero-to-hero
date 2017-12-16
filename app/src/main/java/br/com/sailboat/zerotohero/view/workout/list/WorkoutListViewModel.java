package br.com.sailboat.zerotohero.view.workout.list;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.filter.Filter;
import br.com.sailboat.zerotohero.model.sqlite.Workout;

public class WorkoutListViewModel {

    private Filter filter = new Filter();
    private final List<Workout> workoutList = new ArrayList<>();

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public List<Workout> getWorkoutList() {
        return workoutList;
    }

}
