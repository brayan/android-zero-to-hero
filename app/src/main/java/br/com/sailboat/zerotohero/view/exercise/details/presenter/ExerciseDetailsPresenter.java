package br.com.sailboat.zerotohero.view.exercise.details.presenter;

import android.content.Context;
import android.content.Intent;

import br.com.sailboat.zerotohero.base.BasePresenter;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.helper.LogHelper;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.view.async_tasks.SaveExerciseAsyncTask;

public class ExerciseDetailsPresenter extends BasePresenter {

    private View view;
    private ExerciseDetailsViewModel viewModel;

    public ExerciseDetailsPresenter(View view) {
        setView(view);
        setViewModel(new ExerciseDetailsViewModel());
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

    public ExerciseDetailsViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(ExerciseDetailsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    private void saveExercise() {
        Context context = getView().getActivityContext().getApplicationContext();
        Exercise exercise = getViewModel().getExercise();

        new SaveExerciseAsyncTask(context, exercise, new SaveExerciseAsyncTask.Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onFail(Exception e) {
                LogHelper.printExceptionLog(e);
                getView().showToast(e.getMessage());
            }
        }).execute();
    }

    public interface View {

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
