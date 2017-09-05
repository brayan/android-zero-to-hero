package br.com.sailboat.zerotohero.view.exercise.list;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;
import br.com.sailboat.zerotohero.view.adapter.ExercisesListAdapter;

public class ExerciseListPresenter extends BasePresenter<ExerciseListPresenter.View> implements ExercisesListAdapter.Callback {

    private ExerciseListViewModel viewModel = new ExerciseListViewModel();
    private int limit = 0;

    public ExerciseListPresenter(ExerciseListPresenter.View view) {
        super(view);
        setLimit(50);
    }

    @Override
    protected void onResumeFirstSession() {
        loadExercises();
    }

    @Override
    protected void onResumeAfterRestart() {
        updateContentViews();
    }

    public void postActivityResult() {
        loadExercises();
    }

    @Override
    public void onClickExercise(int position) {
        Exercise exercise = getExercises().get(position);
        getView().startExerciseDetailsActivity(exercise.getId());
    }

    public void onClickNewExercise() {
        getView().startNewExerciseActivity();
    }

    @Override
    public List<Exercise> getExercises() {
        return viewModel.getExercises();
    }

    public void onLoadMoreExercises(int currentPage) {
        // TODO

        // TOTAL: 1000 EXERCISES;
        // INITIAL LOAD: 50;
        // CURRENT PAGE: 1;
    }

    private void loadExercises() {

        AsyncHelper.execute(new AsyncHelper.Callback() {

            List<Exercise> exercises = new ArrayList<>();

            @Override
            public void doInBackground() throws Exception {
                exercises = ExerciseSQLite.newInstance(getContext()).getAll(null);
            }

            @Override
            public void onSuccess() {
                getExercises().clear();
                getExercises().addAll(exercises);
                updateContentViews();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
                updateContentViews();
            }
        });

    }

    private void updateContentViews() {
        getView().updateExercises();
        updateVisibilityOfViews();
    }

    private void updateVisibilityOfViews() {
        if (viewModel.getExercises().isEmpty()) {
            getView().hideExercises();
            getView().showEmptyView();
        } else {
            getView().showExercises();
            getView().hideEmptyView();
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }


    public interface View extends BasePresenter.View {
        void startExerciseDetailsActivity(long exerciseId);
        void startNewExerciseActivity();
        void updateExercises();
        void showExercises();
        void hideExercises();
    }


}
