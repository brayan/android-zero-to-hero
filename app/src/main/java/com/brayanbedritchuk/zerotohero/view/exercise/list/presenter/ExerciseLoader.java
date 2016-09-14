package com.brayanbedritchuk.zerotohero.view.exercise.list.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.persistence.dao.ExerciseDAOSQLite;

import java.util.ArrayList;
import java.util.List;

class ExerciseLoader extends AsyncTask<Void, Integer, Exception> {

    private ExerciseListView view;
    private ExerciseListViewModel viewModel;
    private List<Exercise> exercises;

    public ExerciseLoader(ExerciseListView view, ExerciseListViewModel viewModel) {
        setView(view);
        setViewModel(viewModel);
        exercises = new ArrayList<>();
    }

    protected Exception doInBackground(Void... urls) {
        try {
            exercises = new ExerciseDAOSQLite(getView().getActivityContext().getApplicationContext()).getAll();
            return null;
        } catch (Exception e) {
            Log.e("ZERO_TO_HERO_EXCEPTION", "An error occurred while getting the list of workouts", e);
            return e;
        }
    }

    protected void onPostExecute(Exception exeption) {
        verifyAndHandleException(exeption);

        getViewModel().getExercises().clear();
        getViewModel().getExercises().addAll(exercises);

        getView().updateContentViews();

    }

    private void verifyAndHandleException(Exception exeption) {
        if (exeption != null) {
            getView().showToast(exeption.getMessage());
        }
    }

    public ExerciseListView getView() {
        return view;
    }

    public void setView(ExerciseListView view) {
        this.view = view;
    }

    public ExerciseListViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(ExerciseListViewModel viewModel) {
        this.viewModel = viewModel;
    }

}
