package com.brayanbedritchuk.zerotohero.view.exercise.list.presenter;

import com.brayanbedritchuk.zerotohero.model.Exercise;

import java.util.List;

public class ExerciseListPresenter {

    private ExerciseListView view;
    private ExerciseListViewModel viewModel;

    public ExerciseListPresenter(ExerciseListView view) {
        setView(view);
        setViewModel(new ExerciseListViewModel());
    }

    public void onResume() {
        verifyAndLoadData();
        getViewModel().setFirstSession(false);
    }

    public void onClickExercise(int position) {
        Exercise exercise = getExercises().get(position);
        getView().startExerciseDetailsActivity(exercise);
    }

    private void verifyAndLoadData() {
        if (getViewModel().isFirstSession()) {
            new ExerciseLoader(getView(), getViewModel()).execute();
        } else {
            getView().updateContentViews();
        }
    }

    public ExerciseListViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(ExerciseListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public ExerciseListView getView() {
        return view;
    }

    public void setView(ExerciseListView view) {
        this.view = view;
    }

    public List<Exercise> getExercises() {
        return getViewModel().getExercises();
    }

//    public WorkoutDAO getWorkoutDAO() {
//        return WorkoutDAOSQLite.getInstance(getView().getActivityContext());
//    }

}
