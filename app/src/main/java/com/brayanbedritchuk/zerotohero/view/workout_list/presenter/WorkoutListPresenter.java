package com.brayanbedritchuk.zerotohero.view.workout_list.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.brayanbedritchuk.zerotohero.model.entity.Workout;
import com.brayanbedritchuk.zerotohero.model.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListPresenter {

    private WorkoutListView view;
    private MainViewModel viewModel;

    public WorkoutListPresenter(WorkoutListView view) {
        setView(view);
        setViewModel(new MainViewModel());
    }

    public void onResume() {
        verifyAndLoadWorkouts();
        getViewModel().setFirstSession(false);
    }

    private void verifyAndLoadWorkouts() {
        if (getViewModel().isFirstSession()) {
            new WorkoutsLoader().execute();
        } else {
            getView().updateContentViews();
        }
    }

    public void onClickNewWorkout() {
        getView().startNewWorkoutActivity();
    }

    class WorkoutsLoader extends AsyncTask<Void, Integer, Exception> {

        private List<Workout> workoutList = new ArrayList<>();

        protected Exception doInBackground(Void... urls) {
            try {
//                this.beers = getBeerDAO().getWorkouts();
                Workout workout = new Workout();
                workout.setName("Monday Workout");

                Workout workout2 = new Workout();
                workout2.setName("Wednesday Workout");

                Workout workout3 = new Workout();
                workout3.setName("Friday Workout");

                workoutList.add(workout);
                workoutList.add(workout2);
                workoutList.add(workout3);

                return null;
            } catch (Exception e) {
                Log.e("ZERO_TO_HERO_EXCEPTION", "An error occurred while getting the list of workouts", e);
                return e;
            }
        }

        protected void onPostExecute(Exception exeption) {
            verifyAndHandleException(exeption);

            getViewModel().getWorkoutList().clear();
            getViewModel().getWorkoutList().addAll(workoutList);

            getView().updateContentViews();

        }
    }

    private void verifyAndHandleException(Exception exeption) {
        if (exeption != null) {
            getView().showToast(exeption.getMessage());
        }
    }

    public MainViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(MainViewModel viewModel) {
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

//    public WorkoutDAO getWorkoutDAO() {
//        return WorkoutDAOSQLite.getInstance(getView().getActivityContext());
//    }

}
