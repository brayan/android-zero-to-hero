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
    protected void postResume() {
        loadExercises();
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
        return getViewModel().getExercises();
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
                getView().updateContentViews();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
                getView().updateContentViews();
            }
        });

    }

    private ExerciseListViewModel getViewModel() {
        return viewModel;
    }


    public interface View extends BasePresenter.View{
        void updateContentViews();
        void startExerciseDetailsActivity(long exerciseId);
        void startNewExerciseActivity();
    }

}
