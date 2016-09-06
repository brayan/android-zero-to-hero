package com.brayanbedritchuk.zerotohero.view.workout_details.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.brayanbedritchuk.zerotohero.model.entity.Exercise;
import com.brayanbedritchuk.zerotohero.model.viewmodel.WorkoutDetailsViewModel;

import java.util.ArrayList;
import java.util.List;

class ExercisesLoader extends AsyncTask<Void, Integer, Exception> {

    private WorkoutDetailsView view;
    private WorkoutDetailsViewModel viewModel;
    private List<Exercise> exerciseList;

    public ExercisesLoader(WorkoutDetailsView view, WorkoutDetailsViewModel viewModel) {
        setView(view);
        setViewModel(viewModel);
        setExerciseList(new ArrayList<Exercise>());
    }

    protected Exception doInBackground(Void... urls) {
        try {
//                this.beers = getBeerDAO().getWorkouts();
            Exercise exercise = new Exercise();
            exercise.setName("Exercise 1");

            Exercise exercise2 = new Exercise();
            exercise2.setName("Exercise 2");

            Exercise exercise3 = new Exercise();
            exercise3.setName("Exercise 3");


            getExerciseList().add(exercise);
            getExerciseList().add(exercise2);
            getExerciseList().add(exercise3);

            return null;
        } catch (Exception e) {
            Log.e("ZERO_TO_HERO_EXCEPTION", "An error occurred while getting the list of workouts", e);
            return e;
        }
    }

    protected void onPostExecute(Exception exeption) {
        verifyAndHandleException(exeption);

        getViewModel().getExerciseList().clear();
        getViewModel().getExerciseList().addAll(getExerciseList());

        getView().updateContentViews();

    }

    private void verifyAndHandleException(Exception exeption) {
        if (exeption != null) {
            getView().showToast(exeption.getMessage());
        }
    }

    public WorkoutDetailsView getView() {
        return view;
    }

    public void setView(WorkoutDetailsView view) {
        this.view = view;
    }

    public WorkoutDetailsViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(WorkoutDetailsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
}
