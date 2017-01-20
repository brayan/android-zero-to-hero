package br.com.sailboat.zerotohero.view.ui.exercise.details.presenter;

import android.content.Context;
import android.content.Intent;

import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.view.async_tasks.SaveExerciseAsyncTask;
import br.com.sailboat.zerotohero.view.ui.exercise.details.view_model.ExerciseDetailsViewModel;

public class ExerciseDetailsPresenter extends br.com.sailboat.canoe.base.BasePresenter<ExerciseDetailsPresenter.View> {

    private ExerciseDetailsViewModel viewModel = new ExerciseDetailsViewModel();

    public ExerciseDetailsPresenter(ExerciseDetailsPresenter.View view) {
        super(view);
    }

    @Override
    protected void postResume() {
        updateContentViews();
    }

    @Override
    public void extractExtrasFromIntent(Intent intent) {
        Exercise exercise = ExtrasHelper.getExercise(intent);
        getViewModel().setExercise(exercise);
    }

    public void onClickEditExercise() {
        Exercise exercise = getViewModel().getExercise();
        getView().startEditExerciseActivity(exercise);
    }

    public void onClickNavigation() {
        getView().closeActivityWithResultCanceled();
    }

    public void onResultOkEditExercise(Intent data) {
        getViewModel().setExercise(ExtrasHelper.getExercise(data));
        updateContentViews();
        saveExercise();
    }

    public void onClickMenuDelete() {
        Exercise exercise = getViewModel().getExercise();
        getView().closeActivityWithResultOkAndDeleteExercise(exercise);
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

    public Context getContext() {
        return getView().getActivityContext();
    }

    public Exercise getExercise() {
        return getViewModel().getExercise();
    }



    public interface View extends br.com.sailboat.canoe.base.BasePresenter.View {
        Context getActivityContext();
        void showToast(String message);
        void startEditExerciseActivity(Exercise exercise);
        void closeActivityWithResultCanceled();
        void closeActivityWithResultOkAndDeleteExercise(Exercise exercise);
        void setRepetition(String name);
        void setWeight(String weight);
        void setSet(String set);
        void setName(String name);
    }

}
