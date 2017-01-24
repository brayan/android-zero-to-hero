package br.com.sailboat.zerotohero.view.ui.exercise.details;

import android.content.Intent;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutExerciseSQLite;
import br.com.sailboat.zerotohero.view.async_tasks.SaveExerciseAsyncTask;

public class ExerciseDetailsPresenter extends BasePresenter<ExerciseDetailsPresenter.View> {

    private ExerciseDetailsViewModel viewModel = new ExerciseDetailsViewModel();

    public ExerciseDetailsPresenter(ExerciseDetailsPresenter.View view) {
        super(view);
    }

    @Override
    public void extractExtrasFromIntent(Intent intent) {
        long exerciseId = Extras.getExerciseId(intent);
        getViewModel().setExerciseId(exerciseId);
    }

    @Override
    protected void postResume() {
        loadDetails();
    }

    public void onClickEditExercise() {
        Exercise exercise = getViewModel().getExercise();
        getView().startEditExerciseActivity(exercise);
    }

    public void postActivityResult() {
        loadDetails();
    }

    public void onClickMenuDelete() {
        getView().showDialogDeleteExercise();
    }

    public void onClickDeleteExercise() {
        AsyncHelper.execute(new AsyncHelper.Callback() {

            @Override
            public void doInBackground() throws Exception {
                long exerciseId = getViewModel().getExerciseId();
                new ExerciseSQLite(getContext()).delete(exerciseId);
                new WorkoutExerciseSQLite(getContext()).deleteFromExercise(exerciseId);
            }

            @Override
            public void onSuccess() {
                closeActivityWithResultOk();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
            }

        });
    }

    private void updateContentViews() {
        Exercise exercise = getViewModel().getExercise();
        getView().setName(exercise.getName());
        getView().setWeight(String.valueOf(exercise.getWeight()));
        getView().setSet(String.valueOf(exercise.getSet()));
        getView().setRepetition(String.valueOf(exercise.getRepetition()));
    }

    private ExerciseDetailsViewModel getViewModel() {
        return viewModel;
    }

    private void loadDetails() {
        AsyncHelper.execute(new AsyncHelper.Callback() {

            Exercise exercise;

            @Override
            public void doInBackground() throws Exception {
                long exerciseId = getViewModel().getExerciseId();
                exercise = new ExerciseSQLite(getContext()).getExerciseById(exerciseId);
            }

            @Override
            public void onSuccess() {
                getViewModel().setExercise(exercise);
                updateContentViews();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
                closeActivityWithResultCanceled();
            }

        });
    }

    private void saveExercise() {

        new SaveExerciseAsyncTask(getContext(), getExercise(), new SaveExerciseAsyncTask.Callback() {

            @Override
            public void onSuccess() {
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
            }

        }).execute();

    }

    public Exercise getExercise() {
        return getViewModel().getExercise();
    }


    public interface View extends BasePresenter.View {
        void startEditExerciseActivity(Exercise exercise);
        void setRepetition(String name);
        void setWeight(String weight);
        void setSet(String set);
        void setName(String name);
        void showDialogDeleteExercise();
    }

}
