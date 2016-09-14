package com.brayanbedritchuk.zerotohero.view.workout.details.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.persistence.dao.ExerciseDAOSQLite;

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
                this.exerciseList = new ExerciseDAOSQLite(getView().getActivityContext().getApplicationContext()).getFromWorkout(getViewModel().getWorkout().getId());

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

        getView().updateExerciseListView();

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
