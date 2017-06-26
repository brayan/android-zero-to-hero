package br.com.sailboat.zerotohero.view.exercise.selector;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;

public class ExerciseSelectorPresenter extends BasePresenter<ExerciseSelectorPresenter.View> {

    private ExerciseSelectorViewModel viewModel = new ExerciseSelectorViewModel();

    public ExerciseSelectorPresenter(ExerciseSelectorPresenter.View view) {
        super(view);
    }

    @Override
    public void extractExtrasFromArguments(Bundle arguments) {
        List<Exercise> exercises = ExtrasHelper.getExercises(arguments);
        addSelectedExercises(exercises);
    }

    @Override
    protected void onResumeFirstSession() {
        String searchText = getView().getSearchText();
        loadExercises(searchText);
    }

    @Override
    protected void onResumeAfterRestart() {
        updateContentViews();
    }

    public void onClickFabSave() {
        List<Exercise> exercises = getExercisesListFromLinkedHashMap();
        getView().closeActivityResultOk(exercises);
    }

    public void onClickExercise(int position) {
        Exercise exercise = getExerciseList().get(position);

        updateSelectedExercisesArray(exercise);
        updateTitle();
        updateExerciseView(position);
    }

    public List<Exercise> getExerciseList() {
        return viewModel.getExerciseList();
    }

    public LinkedHashMap<Long, Exercise> getSelectedExercises() {
        return viewModel.getSelectedExercises();
    }

    public boolean isExerciseSelected(Exercise exercise) {
        return (getSelectedExercises().get(exercise.getId()) != null);
    }

    @NonNull
    private List<Exercise> getExercisesListFromLinkedHashMap() {
        LinkedHashMap<Long, Exercise> selectedExercises = viewModel.getSelectedExercises();
        return new ArrayList<>(selectedExercises.values());
    }

    @Override
    protected void onQueryTextChange(String text) {
        loadExercises(text);
    }

    private void loadExercises(final String searchText) {

        AsyncHelper.execute(new AsyncHelper.Callback() {

            List<Exercise> exercises = new ArrayList<>();

            @Override
            public void doInBackground() throws Exception {
                exercises = ExerciseSQLite.newInstance(getContext()).getAll(searchText);
            }

            @Override
            public void onSuccess() {
                viewModel.getExerciseList().clear();
                viewModel.getExerciseList().addAll(exercises);
                updateContentViews();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
            }
        });

    }

    private void updateTitle() {
        int size = viewModel.getSelectedExercises().size();

        String title = null;

        if (size == 0) {
            title = getString(R.string.no_items_selected);
        } else if (size == 1) {
            title = "1 " + getString(R.string.item);
        } else {
            title = size + " " + getString(R.string.items);
        }

        getView().setTitle(title);
    }

    private void updateContentViews() {
        updateTitle();
        getView().updateExerciseList();
        updateVisibilityOfViews();
    }

    private void updateVisibilityOfViews() {
        if (getExerciseList().isEmpty()) {
            getView().hideRecycler();
            getView().showEmptyView();
        } else {
            getView().showRecycler();
            getView().hideEmptyView();
        }
    }

    private void addSelectedExercises(List<Exercise> exercises) {
        LinkedHashMap<Long, Exercise> selectedExercises = viewModel.getSelectedExercises();

        for (Exercise e : exercises) {
            selectedExercises.put(e.getId(), e);
        }
    }

    private void updateExerciseView(int position) {
        getView().updateExerciseView(position);
    }

    private void updateSelectedExercisesArray(Exercise exercise) {
        LinkedHashMap<Long, Exercise> selectedExercises = viewModel.getSelectedExercises();

        if (isExerciseSelected(exercise)) {
            selectedExercises.remove(exercise.getId());
        } else {
            selectedExercises.put(exercise.getId(), exercise);
        }
    }


    public interface View extends BasePresenter.View {
        void updateExerciseList();
        void setTitle(String title);
        void updateExerciseView(int position);
        void closeActivityResultOk(List<Exercise> exercise);
        void hideRecycler();
        void showEmptyView();
        void showRecycler();
        void hideEmptyView();
    }

}
