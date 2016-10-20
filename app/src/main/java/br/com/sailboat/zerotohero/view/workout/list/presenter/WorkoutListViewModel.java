package br.com.sailboat.zerotohero.view.workout.list.presenter;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.base.BaseViewModel;
import br.com.sailboat.zerotohero.model.Workout;

public class WorkoutListViewModel extends BaseViewModel {

    private transient final List<Workout> workoutList;

    public WorkoutListViewModel() {
        this.workoutList = new ArrayList<>();
    }

    public List<Workout> getWorkoutList() {
        return workoutList;
    }

}
