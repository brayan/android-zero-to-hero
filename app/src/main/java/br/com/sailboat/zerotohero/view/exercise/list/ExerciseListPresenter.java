package br.com.sailboat.zerotohero.view.exercise.list;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.zerotohero.model.view.ExerciseView;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseViewSQLite;
import br.com.sailboat.zerotohero.view.adapter.ExercisesListAdapter;

public class ExerciseListPresenter extends BasePresenter<ExerciseListPresenter.View> implements ExercisesListAdapter.Callback {

    private ExerciseListViewModel viewModel = new ExerciseListViewModel();

    public ExerciseListPresenter(ExerciseListPresenter.View view) {
        super(view);
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
        ExerciseView exercise = getExercises().get(position);
        getView().startExerciseDetailsActivity(exercise.getExerciseId());
    }

    public void onClickNewExercise() {
        getView().startNewExerciseActivity();
    }

    @Override
    public List<ExerciseView> getExercises() {
        return viewModel.getExercises();
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
            getView().showEmptyState();
        } else {
            getView().showExercises();
            getView().hideEmptyState();
        }
    }


    public interface View extends BasePresenter.View{
        void startExerciseDetailsActivity(long exerciseId);
        void startNewExerciseActivity();
        void updateExercises();
        void showExercises();
        void hideExercises();
        void showEmptyState();
        void hideEmptyState();
    }

}
