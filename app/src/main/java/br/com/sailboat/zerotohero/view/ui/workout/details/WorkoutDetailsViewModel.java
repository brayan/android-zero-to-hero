package br.com.sailboat.zerotohero.view.ui.workout.details;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.base.BaseViewModel;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.model.Workout;

public class WorkoutDetailsViewModel extends BaseViewModel {

    public static final String TAG = WorkoutDetailsViewModel.class.getName();

    private Workout workout;
    private transient List<Exercise> exerciseList = new ArrayList<>();

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
}
