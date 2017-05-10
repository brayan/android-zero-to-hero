package br.com.sailboat.zerotohero.view.exercise.details;

import android.content.Intent;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.persistence.DatabaseOpenHelper;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutExerciseSQLite;

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
        getView().startEditExerciseActivity(exercise.getId());
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
                new ExerciseSQLite(DatabaseOpenHelper.getInstance(getContext())).delete(exerciseId);
                new WorkoutExerciseSQLite(DatabaseOpenHelper.getInstance(getContext())).deleteFromExercise(exerciseId);
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
        getView().setTitle(exercise.getName());
        // TODO: REMOVE AND BIND TO RECYCLERVIEW ADAPTER
//        getView().setWeight(String.valueOf(exercise.getWeight()));
//        getView().setSet(String.valueOf(exercise.getSet()));
//        getView().setRepetition(String.valueOf(exercise.getRepetition()));
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
                exercise = new ExerciseSQLite(DatabaseOpenHelper.getInstance(getContext())).getExerciseById(exerciseId);
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

    public Exercise getExercise() {
        return getViewModel().getExercise();
    }


    public interface View extends BasePresenter.View {
        void startEditExerciseActivity(long exerciseId);
        void setRepetition(String name);
        void setWeight(String weight);
        void setSet(String set);
        void showDialogDeleteExercise();
        void setTitle(String title);
    }

}
