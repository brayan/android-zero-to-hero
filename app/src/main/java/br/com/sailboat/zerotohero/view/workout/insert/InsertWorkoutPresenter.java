package br.com.sailboat.zerotohero.view.workout.insert;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.exception.EntityNotFoundException;
import br.com.sailboat.canoe.exception.RequiredFieldNotFilledException;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.canoe.helper.EntityHelper;
import br.com.sailboat.canoe.helper.StringHelper;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.model.sqlite.Workout;
import br.com.sailboat.zerotohero.persistence.DatabaseOpenHelper;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutExerciseSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutSQLite;

public class InsertWorkoutPresenter extends BasePresenter<InsertWorkoutPresenter.View> {

    private InsertWorkoutViewModel viewModel = new InsertWorkoutViewModel();

    public InsertWorkoutPresenter(InsertWorkoutPresenter.View view) {
        super(view);
    }

    @Override
    public void extractExtrasFromArguments(Bundle arguments) {
        long workoutId = ExtrasHelper.getWorkoutId(arguments);
        viewModel.setWorkoutId(workoutId);
    }

    @Override
    protected void onResumeFirstSession() {
        if (hasWorkoutToEdit()) {
            startEditingWorkout();
        }
    }

    @Override
    protected void postResume() {
        updateContentViews();
    }

    public void onClickAddExercises() {
        ArrayList array = (ArrayList) viewModel.getExercises();
        getView().startExercisesChooserActivity(array);
    }

    public void onClickMenuSave() {
        try {
            closeKeyboard();
            extractInfoFromViews();
            performValidations();
            saveWorkout();

        } catch (RequiredFieldNotFilledException e) {
            showMessage(e.getMessage());

        } catch (Exception e) {
            printLogAndShowDialog(e);
        }
    }

    public List<Exercise> getExercises() {
        return viewModel.getExercises();
    }

    private void saveWorkout() {
        AsyncHelper.execute(new AsyncHelper.Callback() {

            Workout workout;

            @Override
            public void doInBackground() throws Exception {
                prepareAndSaveWorkout();
                prepareAndSaveExercises();
            }

            @Override
            public void onSuccess() {
                closeActivityWithResultOk();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
            }

            private void prepareAndSaveExercises() throws Exception {
                WorkoutExerciseSQLite dao = new WorkoutExerciseSQLite(DatabaseOpenHelper.getInstance(getContext()));
                dao.deleteFromWorkout(workout.getId());

                for (int i = 0; i < viewModel.getExercises().size(); i++) {
                    Exercise exercise = viewModel.getExercises().get(i);
                    dao.save(workout.getId(), exercise.getId(), i);
                }

            }

            private void prepareAndSaveWorkout() throws Exception {
                workout = new Workout();
                workout.setId(viewModel.getWorkoutId());
                workout.setName(viewModel.getName());

                if (hasWorkoutToEdit()) {
                    new WorkoutSQLite(DatabaseOpenHelper.getInstance(getContext())).update(workout);
                } else {
                    new WorkoutSQLite(DatabaseOpenHelper.getInstance(getContext())).save(workout);
                }

            }

        });
    }

    private void extractInfoFromViews() {
        String name = getView().getTextFromWorkoutName();
        viewModel.setName(name);
    }

    public void onResultOkExerciseChooser(Intent data) {
        List<Exercise> exercises = ExtrasHelper.getExercises(data);
        getExercises().clear();
        getExercises().addAll(exercises);
        updateContentViews();
    }

    private void loadExercises() throws Exception {
        long workoutId = viewModel.getWorkoutId();

        getExercises().clear();
        getExercises().addAll(ExerciseSQLite.newInstance(getContext()).getFromWorkout(workoutId));
    }

    private void updateContentViews() {
        updateToolbarTitle();
        updateVisibilityOfViews();
        getView().updateExercises();
    }

    private void updateVisibilityOfViews() {
        if (getExercises().isEmpty()) {
            getView().hideExercises();
            getView().showEmptyView();
        } else {
            getView().showExercises();
            getView().hideEmptyView();
        }
    }

    private void updateToolbarTitle() {
        if (hasWorkoutToEdit()) {
            getView().setTitle(getString(R.string.edit_workout));
        } else {
            getView().setTitle(getString(R.string.new_workout));
        }
    }

    private boolean hasWorkoutToEdit() {
        return viewModel.getWorkoutId() != EntityHelper.NO_ID;
    }

    private void performValidations() throws Exception {
        String name = viewModel.getName();

        if (StringHelper.isNullOrEmpty(name)) {
            throw new Exception(getString(R.string.exeption_workout_name));
        }

        if (viewModel.getExercises().isEmpty()) {
            throw new Exception(getString(R.string.exeption_selected_exercises));
        }
    }

    private void startEditingWorkout() {
        AsyncHelper.execute(new AsyncHelper.Callback() {

            @Override
            public void doInBackground() throws Exception {
                long workoutId = viewModel.getWorkoutId();

                loadWorkoutInfo(workoutId);
                loadExercises();
            }

            @Override
            public void onSuccess() {
                getView().setActivityToHideKeyboard();
                getView().setWorkoutName(viewModel.getName());
                updateContentViews();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
                closeActivityWithResultCanceled();
            }

        });
    }

    private void loadWorkoutInfo(long workoutId) throws EntityNotFoundException {
        Workout workout = new WorkoutSQLite(DatabaseOpenHelper.getInstance(getContext())).getWorkoutById(workoutId);
        viewModel.setName(workout.getName());
    }


    public interface View extends BasePresenter.View {
        void startExercisesChooserActivity(ArrayList<Exercise> exercises);
        String getTextFromWorkoutName();
        void setWorkoutName(String name);
        void setTitle(String title);
        void updateExercises();
        void hideExercises();
        void showExercises();
    }

}
