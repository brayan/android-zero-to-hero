package com.brayanbedritchuk.zerotohero.view.workout.list.presenter;

import com.brayanbedritchuk.zerotohero.model.Workout;

import java.util.List;

public class WorkoutListPresenter {

    private WorkoutListView view;
    private WorkoutListViewModel viewModel;

    public WorkoutListPresenter(WorkoutListView view) {
        setView(view);
        setViewModel(new WorkoutListViewModel());
    }

    public void onResume() {
        verifyAndLoadWorkouts();
        getViewModel().setFirstSession(false);
    }

    public void onClickNewWorkout() {
        getView().startNewWorkoutActivity();
    }

    public void onClickWorkout(int position) {
        Workout workout = getWorkouts().get(position);
        getView().startWorkoutDetailsActivity(workout);
    }

    private void verifyAndLoadWorkouts() {
        if (getViewModel().isFirstSession()) {
            loadWorkouts();
        } else {
            getView().updateContentViews();
        }
    }

    private void loadWorkouts() {
        new WorkoutsLoader(getView(), getViewModel()).execute();
    }

    public WorkoutListViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(WorkoutListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public WorkoutListView getView() {
        return view;
    }

    public void setView(WorkoutListView view) {
        this.view = view;
    }

    public List<Workout> getWorkouts() {
        return getViewModel().getWorkoutList();
    }

    public void onActivityResult() {
        loadWorkouts();
    }

    //    public WorkoutDAO getWorkoutDAO() {
//        return WorkoutDAOSQLite.getInstance(getView().getActivityContext());
//    }

}
