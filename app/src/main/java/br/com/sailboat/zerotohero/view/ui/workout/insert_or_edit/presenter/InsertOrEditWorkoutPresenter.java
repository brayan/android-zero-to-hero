package br.com.sailboat.zerotohero.view.ui.workout.insert_or_edit.presenter;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.helper.StringHelper;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.model.Workout;
import br.com.sailboat.zerotohero.view.async_tasks.LoadExercisesFromWorkoutAsyncTask;
import br.com.sailboat.zerotohero.view.ui.workout.insert_or_edit.view_model.InsertOrEditWorkoutViewModel;

public class InsertOrEditWorkoutPresenter extends BasePresenter<InsertOrEditWorkoutPresenter.View> {

    private InsertOrEditWorkoutViewModel viewModel = new InsertOrEditWorkoutViewModel();

    public InsertOrEditWorkoutPresenter(InsertOrEditWorkoutPresenter.View view) {
        super(view);
    }

    @Override
    protected void onResumeFirstSession() {
        if (hasWorkoutToEdit()) {
            getView().updateWorkoutNameView(getViewModel().getWorkout().getName());
            getView().hideKeyboard();
            loadExercises();
        }
    }

    @Override
    protected void postResume() {
        updateContentViews();
    }

    @Override
    public void extractExtrasFromIntent(Intent intent) {
        Workout workout = ExtrasHelper.getWorkout(intent);
        getViewModel().setWorkout(workout);
    }

    public void onClickAddExercises() {
        ArrayList array = (ArrayList) getViewModel().getExercises();
        getView().startExercisesChooserActivity(array);
    }

    public void onClickMenuSave() {
        try {
            checkRequiredComponents();
            buildAndReturnEntities();
        } catch (Exception e) {
            getView().showDialog(e.getMessage());
        }
    }

    public void onResultOkExerciseChooser(Intent data) {
        List<Exercise> exercises = ExtrasHelper.getExercises(data);
        getViewModel().getExercises().clear();
        getViewModel().getExercises().addAll(exercises);
        getView().updateExercisesListAndVisibility();
    }

    private void loadExercises() {
        long workoutId = getViewModel().getWorkout().getId();

        new LoadExercisesFromWorkoutAsyncTask(getContext(), workoutId, new LoadExercisesFromWorkoutAsyncTask.Callback() {

            @Override
            public void onSuccess(List<Exercise> exercises) {
                getViewModel().getExercises().clear();
                getViewModel().getExercises().addAll(exercises);
                getView().updateExercisesListAndVisibility();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
            }

        }).execute();

    }

    private void updateContentViews() {
        updateToolbarTitle();
        getView().updateExercisesListAndVisibility();
    }

    private void updateToolbarTitle() {
        String title = null;

        if (hasWorkoutToEdit()) {
            title = getString(R.string.edit_workout);
        } else {
            title = getString(R.string.new_workout);
        }

        getView().updateToolbarTitle(title);
    }

    private boolean hasWorkoutToEdit() {
        return getViewModel().getWorkout() != null;
    }

    private void buildAndReturnEntities() {
        Workout workout = getViewModel().getWorkout();

        if (workout == null) {
            workout = new Workout();
            workout.setId(-1);
        }

        workout.setName(getView().getTextFromWorkoutName());

        getView().closeActivityWithResultOk(workout, getViewModel().getExercises());
    }

    private void checkRequiredComponents() throws Exception {
        String name = getView().getTextFromWorkoutName();

        if (StringHelper.isNullOrEmpty(name)) {
            throw new Exception(getString(R.string.exeption_workout_name));
        }

        if (getViewModel().getExercises().size() == 0) {
            throw new Exception(getString(R.string.exeption_selected_exercises));
        }
    }

    private InsertOrEditWorkoutViewModel getViewModel() {
        return viewModel;
    }

    public List<Exercise> getExercises() {
        return getViewModel().getExercises();
    }


    public interface View extends BasePresenter.View {
        Context getActivityContext();
        void updateVisibilityOfViews();
        void updateExercisesListAndVisibility();
        void showToast(String message);
        void startExercisesChooserActivity(ArrayList<Exercise> exercises);
        String getTextFromWorkoutName();
        void showDialog(String message);
        void closeActivityWithResultOk(Workout workout, List<Exercise> exercises);
        void updateWorkoutNameView(String name);
        void hideKeyboard();
        void updateToolbarTitle(String title);
    }

}
