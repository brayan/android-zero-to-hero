package br.com.sailboat.zerotohero.view.exercise.list;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.filter.Filter;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;
import br.com.sailboat.zerotohero.view.adapter.ExercisesListAdapter;

public class ExerciseListPresenter extends BasePresenter<ExerciseListPresenter.View> implements ExercisesListAdapter.Callback {

    private ExerciseListViewModel viewModel = new ExerciseListViewModel();

    public ExerciseListPresenter(ExerciseListPresenter.View view) {
        super(view);
        viewModel.setFilter(new Filter());
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

    @Override
    public void onClickFab() {
        getView().startNewExerciseActivity();
    }

    @Override
    protected void onQueryTextChange() {
        viewModel.getFilter().setSearchText(getSearchText());
        loadExercises();
    }

    @Override
    public List<Exercise> getExercises() {
        return viewModel.getExercises();
    }

    @Override
    public void onItemDismiss() {
    }

    private void loadExercises() {

        AsyncHelper.execute(AsyncTask.THREAD_POOL_EXECUTOR, new AsyncHelper.Callback() {

            List<Exercise> exercises = new ArrayList<>();

            @Override
            public void doInBackground() throws Exception {
                exercises = ExerciseSQLite.newInstance(getContext()).getAll(viewModel.getFilter());
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
            getView().hideRecycler();
            getView().showEmptyView();
        } else {
            getView().showRecycler();
            getView().hideEmptyView();
        }
    }



    public interface View extends BasePresenter.View {
        void startExerciseDetailsActivity(long exerciseId);
        void startNewExerciseActivity();
        void updateExercises();
    }


}
