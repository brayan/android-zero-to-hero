package com.brayanbedritchuk.zerotohero.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.brayanbedritchuk.zerotohero.model.entity.Workout;
import com.brayanbedritchuk.zerotohero.model.viewmodel.NewWorkoutViewModel;
import com.brayanbedritchuk.zerotohero.presenter.view.NewWorkoutView;

import java.util.ArrayList;
import java.util.List;

public class NewWorkoutPresenter {

    private NewWorkoutView view;
    private NewWorkoutViewModel viewModel;

    public NewWorkoutPresenter(NewWorkoutView view) {
        setView(view);
        setViewModel(new NewWorkoutViewModel());
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
        // TODO
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

    public NewWorkoutViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(NewWorkoutViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public NewWorkoutView getView() {
        return view;
    }

    public void setView(NewWorkoutView view) {
        this.view = view;
    }

    public List<Workout> getWorkouts() {
        return getViewModel().getWorkoutList();
    }

//    public WorkoutDAO getWorkoutDAO() {
//        return WorkoutDAOSQLite.getInstance(getView().getActivityContext());
//    }

}
