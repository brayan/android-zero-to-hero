package br.com.sailboat.zerotohero.view.ui.exercise.list;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;
import br.com.sailboat.zerotohero.view.async_tasks.SaveExerciseAsyncTask;

public class ExerciseListPresenter extends BasePresenter<ExerciseListPresenter.View> {

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

    public void onClickExercise(int position) {
        Exercise exercise = getExercises().get(position);
        getView().startExerciseDetailsActivity(exercise.getId());
    }

    public void onClickNewExercise() {
        getView().startNewExerciseActivity();
    }

    private void loadExercises() {

        AsyncHelper.execute(new AsyncHelper.Callback() {

            List<Exercise> exercises = new ArrayList<>();

            @Override
            public void doInBackground() throws Exception {
                exercises = new ExerciseSQLite(getContext()).getAll();
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

    private void saveExercise(Exercise exercise) {

        new SaveExerciseAsyncTask(getContext(), exercise, new SaveExerciseAsyncTask.Callback() {

            @Override
            public void onSuccess() {
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
            }

        }).execute();

    }

    private ExerciseListViewModel getViewModel() {
        return viewModel;
    }

    public List<Exercise> getExercises() {
        return getViewModel().getExercises();
    }


    public interface View extends BasePresenter.View{
        void updateContentViews();
        void startExerciseDetailsActivity(long exerciseId);
        void startNewExerciseActivity();
    }

}
