package com.brayanbedritchuk.zerotohero.view.workout_list.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.brayanbedritchuk.zerotohero.model.entity.Workout;
import com.brayanbedritchuk.zerotohero.model.viewmodel.WorkoutListViewModel;

import java.util.ArrayList;
import java.util.List;

class WorkoutsLoader extends AsyncTask<Void, Integer, Exception> {

    private WorkoutListView view;
    private WorkoutListViewModel viewModel;
    private List<Workout> workoutList;

    public WorkoutsLoader(WorkoutListView view, WorkoutListViewModel viewModel) {
        setView(view);
        setViewModel(viewModel);
        setWorkoutList(new ArrayList<Workout>());
    }

    protected Exception doInBackground(Void... urls) {
        try {
//                this.beers = getBeerDAO().getWorkouts();
            Workout workout = new Workout();
            workout.setName("Monday Workout");

            Workout workout2 = new Workout();
            workout2.setName("Wednesday Workout");

            Workout workout3 = new Workout();
            workout3.setName("Friday Workout");

            getWorkoutList().add(workout);
            getWorkoutList().add(workout2);
            getWorkoutList().add(workout3);

            return null;
        } catch (Exception e) {
            Log.e("ZERO_TO_HERO_EXCEPTION", "An error occurred while getting the list of workouts", e);
            return e;
        }
    }

    protected void onPostExecute(Exception exeption) {
        verifyAndHandleException(exeption);

        getViewModel().getWorkoutList().clear();
        getViewModel().getWorkoutList().addAll(getWorkoutList());

        getView().updateContentViews();

    }

    private void verifyAndHandleException(Exception exeption) {
        if (exeption != null) {
            getView().showToast(exeption.getMessage());
        }
    }

    public WorkoutListView getView() {
        return view;
    }

    public void setView(WorkoutListView view) {
        this.view = view;
    }

    public WorkoutListViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(WorkoutListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public List<Workout> getWorkoutList() {
        return workoutList;
    }

    public void setWorkoutList(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }
}
