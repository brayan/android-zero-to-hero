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
import br.com.sailboat.zerotohero.model.view.ExerciseView;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseViewSQLite;

public class ExerciseSelectorPresenter extends BasePresenter<ExerciseSelectorPresenter.View> {

    private ExerciseSelectorViewModel viewModel = new ExerciseSelectorViewModel();

    public ExerciseSelectorPresenter(ExerciseSelectorPresenter.View view) {
        super(view);
    }

    @Override
    public void extractExtrasFromArguments(Bundle arguments) {
        List<ExerciseView> exercises = ExtrasHelper.getExerciseViewList(arguments);
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
        List<ExerciseView> exercises = getExercisesListFromLinkedHashMap();
        getView().closeActivityResultOk(exercises);
    }

    public void onClickExercise(int position) {
        ExerciseView exercise = getExerciseList().get(position);

        updateSelectedExercisesArray(exercise);
        updateTitle();
        updateExerciseView(position);
    }

    public List<ExerciseView> getExerciseList() {
        return viewModel.getExerciseList();
    }

    public LinkedHashMap<Long, ExerciseView> getSelectedExercises() {
        return viewModel.getSelectedExercises();
    }

    public boolean isExerciseSelected(ExerciseView exercise) {
        return (getSelectedExercises().get(exercise.getExerciseId()) != null);
    }

    @NonNull
    private List<ExerciseView> getExercisesListFromLinkedHashMap() {
        LinkedHashMap<Long, ExerciseView> selectedExercises = viewModel.getSelectedExercises();
        return new ArrayList<>(selectedExercises.values());
    }

    @Override
    protected void onQueryTextChange(String text) {
        loadExercises(text);
    }

    private void loadExercises(final String searchText) {

        AsyncHelper.execute(new AsyncHelper.Callback() {

            List<ExerciseView> exercises = new ArrayList<>();

            @Override
            public void doInBackground() throws Exception {
                exercises = ExerciseViewSQLite.newInstance(getContext()).getAll(searchText);
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

    private void addSelectedExercises(List<ExerciseView> exercises) {
        LinkedHashMap<Long, ExerciseView> selectedExercises = viewModel.getSelectedExercises();

        for (ExerciseView e : exercises) {
            selectedExercises.put(e.getExerciseId(), e);
        }
    }

    private void updateExerciseView(int position) {
        getView().updateExerciseView(position);
    }

    private void updateSelectedExercisesArray(ExerciseView exercise) {
        LinkedHashMap<Long, ExerciseView> selectedExercises = viewModel.getSelectedExercises();

        if (isExerciseSelected(exercise)) {
            selectedExercises.remove(exercise.getExerciseId());
        } else {
            selectedExercises.put(exercise.getExerciseId(), exercise);
        }
    }


    public interface View extends BasePresenter.View {
        void updateExerciseList();
        void setTitle(String title);
        void updateExerciseView(int position);
        void closeActivityResultOk(List<ExerciseView> exercise);
        void hideRecycler();
        void showEmptyView();
        void showRecycler();
        void hideEmptyView();
    }

}
