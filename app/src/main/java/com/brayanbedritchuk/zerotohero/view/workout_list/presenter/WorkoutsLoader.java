package com.brayanbedritchuk.zerotohero.view.workout_list.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.brayanbedritchuk.zerotohero.model.Workout;
import com.brayanbedritchuk.zerotohero.persistence.dao.WorkoutDAOSQLite;

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
            setWorkoutList(new WorkoutDAOSQLite(getView().getActivityContext().getApplicationContext()).getAll());
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
