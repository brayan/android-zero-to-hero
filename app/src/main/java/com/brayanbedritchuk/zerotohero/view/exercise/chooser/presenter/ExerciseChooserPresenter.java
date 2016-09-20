package com.brayanbedritchuk.zerotohero.view.exercise.chooser.presenter;

import android.content.Context;
import android.support.annotation.StringRes;
import android.util.SparseArray;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.base.BasePresenter;
import com.brayanbedritchuk.zerotohero.helper.ListHelper;
import com.brayanbedritchuk.zerotohero.helper.LogHelper;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.view.async_tasks.LoadExercisesAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class ExerciseChooserPresenter extends BasePresenter {

    private ExerciseChooserView view;
    private ExerciseChooserViewModel viewModel;

    public ExerciseChooserPresenter(ExerciseChooserView view) {
        setView(view);
        setViewModel(new ExerciseChooserViewModel());
    }

    @Override
    protected void onResumeFirstSession() {
        loadExercises();
    }

    @Override
    protected void postResume() {
        updateContentViews();
    }

    public void onReceiveSelectedExercises(List<Exercise> exercises) {
        SparseArray<Exercise> selectedExercises = getViewModel().getSelectedExercises();

        for (Exercise e : exercises) {
            selectedExercises.put((int) e.getId(), e);
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

    public void onClickNewWorkout() {
        getView().startNewWorkoutActivity();
    }

    public void onClickExercise(int position) {
        Exercise exercise = getExerciseList().get(position);

        if (getViewModel().getSelectedExercises().get((int) exercise.getId()) == null) {
            getViewModel().getSelectedExercises().put((int) exercise.getId(), exercise);
        } else {
            getViewModel().getSelectedExercises().remove((int) exercise.getId());
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

    private void loadExercises() {
        Context context = getView().getActivityContext().getApplicationContext();
        new LoadExercisesAsyncTask(context, new LoadExercisesAsyncTask.Callback() {
            @Override
            public void onSuccess(List<Exercise> exercises) {
                ListHelper.clearAndAdd(exercises, getViewModel().getExerciseList());
                getView().updateExerciseListView();
            }

            @Override
            public void onFail(Exception e) {
                LogHelper.printExceptionLog(e);
                getView().showToast(e.getMessage());
            }
        }).execute();
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

    public boolean hasSelectedExercises() {
        return getViewModel().getSelectedExercises().size() > 0;
    }

}
