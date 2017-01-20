package br.com.sailboat.zerotohero.view.ui.exercise.chooser.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.LongSparseArray;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.view.async_tasks.LoadExercisesAsyncTask;
import br.com.sailboat.zerotohero.view.ui.exercise.chooser.view_model.ExerciseChooserViewModel;

public class ExerciseChooserPresenter extends br.com.sailboat.canoe.base.BasePresenter<ExerciseChooserPresenter.View> {

    private ExerciseChooserViewModel viewModel = new ExerciseChooserViewModel();

    public ExerciseChooserPresenter(ExerciseChooserPresenter.View view) {
        super(view);
    }

    @Override
    protected void onResumeFirstSession() {
        loadExercises();
    }

    @Override
    protected void postResume() {
        updateContentViews();
    }

    @Override
    public void extractExtrasFromIntent(Intent intent) {
        List<Exercise> exercises = ExtrasHelper.getExercises(intent);
        addSelectedExercises(exercises);
    }

    public void onClickMenuSave() {
        List<Exercise> exercises = getExercisesListFromLongSparseArray();
        getView().closeActivityResultOk(exercises);
    }

    public void onClickNavigationIcon() {
        getView().closeActivityResultCanceled();
    }

    public void onClickExercise(int position) {
        Exercise exercise = getExerciseList().get(position);

        updateSelectedExercisesArray(exercise);
        updateTitle();
        updateExerciseView(position);
        updateMenu();
    }

    private void loadExercises() {

        new LoadExercisesAsyncTask(getContext(), new LoadExercisesAsyncTask.Callback() {

            @Override
            public void onSuccess(List<Exercise> exercises) {
                getViewModel().getExerciseList().clear();
                getViewModel().getExerciseList().addAll(exercises);
                getView().updateExerciseListView();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
            }

        }).execute();

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

    private void updateContentViews() {
        getView().updateExerciseListView();
        getView().updateVisibilityOfViews();
    }

    private void addSelectedExercises(List<Exercise> exercises) {
        LongSparseArray<Exercise> selectedExercises = getViewModel().getSelectedExercises();

        for (Exercise e : exercises) {
            selectedExercises.put(e.getId(), e);
        }
    }

    private void updateMenu() {
        getView().updateMenu();
    }

    private void updateExerciseView(int position) {
        getView().updateExerciseView(position);
    }

    private void updateSelectedExercisesArray(Exercise exercise) {
        LongSparseArray<Exercise> selectedExercises = getViewModel().getSelectedExercises();

        if (isExerciseSelected(exercise)) {
            selectedExercises.remove(exercise.getId());
        } else {
            selectedExercises.put(exercise.getId(), exercise);
        }
    }

    private boolean isExerciseSelected(Exercise exercise) {
        boolean isSelected = (getSelectedExercises().get(exercise.getId()) != null);
        return isSelected;
    }

    @NonNull
    private List<Exercise> getExercisesListFromLongSparseArray() {
        List<Exercise> exercises = new ArrayList<>();
        LongSparseArray<Exercise> selectedExercises = getViewModel().getSelectedExercises();

        for (int i = 0; i < selectedExercises.size(); i++) {
            exercises.add(selectedExercises.valueAt(i));
        }

        return exercises;
    }

    public List<Exercise> getExerciseList() {
        return getViewModel().getExerciseList();
    }

    public LongSparseArray<Exercise> getSelectedExercises() {
        return getViewModel().getSelectedExercises();
    }

    private ExerciseChooserViewModel getViewModel() {
        return viewModel;
    }

    public interface View extends BasePresenter.View {
        Context getActivityContext();
        void updateExerciseListView();
        void showToast(String message);
        void updateTitle(String title);
        void updateVisibilityOfViews();
        void updateExerciseView(int position);
        void closeActivityResultCanceled();
        void closeActivityResultOk(List<Exercise> exercises);
        void updateMenu();
    }

}
