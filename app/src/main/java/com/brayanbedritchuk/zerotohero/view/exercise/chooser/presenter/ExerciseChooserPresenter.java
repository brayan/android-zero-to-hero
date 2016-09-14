package com.brayanbedritchuk.zerotohero.view.exercise.chooser.presenter;

import android.support.annotation.StringRes;
import android.util.SparseArray;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseChooserPresenter {

    private ExerciseChooserView view;
    private ExerciseChooserViewModel viewModel;

    public ExerciseChooserPresenter(ExerciseChooserView view) {
        setView(view);
        setViewModel(new ExerciseChooserViewModel());
    }

    public void onResume() {
        updateTitle();
        verifyAndLoadWorkouts();
        getViewModel().setFirstSession(false);
    }

    public void onClickNewWorkout() {
        getView().startNewWorkoutActivity();
    }

    public void onClickExercise(int position) {
        Exercise exercise = getExerciseList().get(position);

        if (getViewModel().getSelectedExercises().get(exercise.getId()) == null) {
            getViewModel().getSelectedExercises().put(exercise.getId(), exercise);
        } else {
            getViewModel().getSelectedExercises().remove(exercise.getId());
        }

        updateTitle();
        getView().updateExerciseView(position);
        getView().updateMenu();
    }

    private void updateTitle() {
        int size = getViewModel().getSelectedExercises().size();

        String title = null;

        if (size == 0) {
            title = getString(R.string.select_exercise);
        } else if (size == 1) {
            title = "1 " + getString(R.string.exercise);
        } else {
            title = size + " " + getString(R.string.exercises);
        }

        getView().updateTitle(title);
    }

    private String getString(@StringRes int id) {
        return getView().getActivityContext().getString(id);
    }

    private void verifyAndLoadWorkouts() {
        if (getViewModel().isFirstSession()) {
            new ExercisesLoader(getView(), getViewModel()).execute();
        } else {
            updateContentViews();
        }
    }

    private void updateContentViews() {
        getView().updateExerciseListView();
        getView().updateVisibilityOfViews();
    }

    public ExerciseChooserViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(ExerciseChooserViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public ExerciseChooserView getView() {
        return view;
    }

    public void setView(ExerciseChooserView view) {
        this.view = view;
    }

    public List<Exercise> getExerciseList() {
        return getViewModel().getExerciseList();
    }

    public SparseArray<Exercise> getSelectedExercises() {
        return getViewModel().getSelectedExercises();
    }

    public void onReceiveSelectedExercises(ArrayList<Exercise> exercises) {
        SparseArray<Exercise> selectedExercises = getViewModel().getSelectedExercises();

        for (Exercise e : exercises) {
            selectedExercises.put(e.getId(), e);
        }
    }

    public void onClickMenuSave() {
        ArrayList<Exercise> exercises = new ArrayList<>();
        SparseArray<Exercise> selectedExercises = getViewModel().getSelectedExercises();

        for (int i = 0; i < selectedExercises.size(); i++) {
            exercises.add(selectedExercises.valueAt(i));
        }

        getView().closeActivityResultOk(exercises);
    }

    public void onClickNavigationIcon() {
        getView().closeActivityResultCanceled();
    }

    public boolean hasSelectedExercises() {
        return getViewModel().getSelectedExercises().size() > 0;
    }

//    public WorkoutDAO getWorkoutDAO() {
//        return WorkoutDAOSQLite.getInstance(getView().getActivityContext());
//    }

}
