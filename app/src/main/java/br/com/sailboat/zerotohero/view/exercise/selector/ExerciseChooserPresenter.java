package br.com.sailboat.zerotohero.view.exercise.selector;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.LongSparseArray;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.model.view.ExerciseView;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseViewSQLite;
import br.com.sailboat.zerotohero.view.adapter.ExerciseChooserAdapter;

public class ExerciseChooserPresenter extends BasePresenter<ExerciseChooserPresenter.View> implements ExerciseChooserAdapter.Callback {

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
    public void extractExtrasFromArguments(Bundle arguments) {
        List<ExerciseView> exercises = Extras.getExerciseViewList(arguments);
        addSelectedExercises(exercises);
    }

    public void onClickMenuSave() {
        List<ExerciseView> exercises = getExercisesListFromLongSparseArray();
        getView().closeActivityResultOk(exercises);
    }

    public void onClickNavigationIcon() {
        getView().closeActivityResultCanceled();
    }

    @Override
    public void onClickExercise(int position) {
        ExerciseView exercise = getExerciseList().get(position);

        updateSelectedExercisesArray(exercise);
        updateTitle();
        updateExerciseView(position);
        updateMenu();
    }

    @Override
    public List<ExerciseView> getExerciseList() {
        return viewModel.getExerciseList();
    }

    @Override
    public LongSparseArray<ExerciseView> getSelectedExercises() {
        return getViewModel().getSelectedExercises();
    }

    private void loadExercises() {

        AsyncHelper.execute(new AsyncHelper.Callback() {

            List<ExerciseView> exercises = new ArrayList<>();

            @Override
            public void doInBackground() throws Exception {
                exercises = ExerciseViewSQLite.newInstance(getContext()).getAll();
            }

            @Override
            public void onSuccess() {
                getViewModel().getExerciseList().clear();
                getViewModel().getExerciseList().addAll(exercises);
                getView().updateExerciseListView();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
            }
        });

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

    private void addSelectedExercises(List<ExerciseView> exercises) {
        LongSparseArray<ExerciseView> selectedExercises = getViewModel().getSelectedExercises();

        for (ExerciseView e : exercises) {
            selectedExercises.put(e.getExerciseId(), e);
        }
    }

    private void updateMenu() {
        getView().updateMenu();
    }

    private void updateExerciseView(int position) {
        getView().updateExerciseView(position);
    }

    private void updateSelectedExercisesArray(ExerciseView exercise) {
        LongSparseArray<ExerciseView> selectedExercises = getViewModel().getSelectedExercises();

        if (isExerciseSelected(exercise)) {
            selectedExercises.remove(exercise.getExerciseId());
        } else {
            selectedExercises.put(exercise.getExerciseId(), exercise);
        }
    }

    private boolean isExerciseSelected(ExerciseView exercise) {
        return (getSelectedExercises().get(exercise.getExerciseId()) != null);
    }

    @NonNull
    private List<ExerciseView> getExercisesListFromLongSparseArray() {
        List<ExerciseView> exercises = new ArrayList<>();
        LongSparseArray<ExerciseView> selectedExercises = getViewModel().getSelectedExercises();

        for (int i = 0; i < selectedExercises.size(); i++) {
            exercises.add(selectedExercises.valueAt(i));
        }

        return exercises;
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
        void closeActivityResultOk(List<ExerciseView> exercises);
        void updateMenu();
    }

}
