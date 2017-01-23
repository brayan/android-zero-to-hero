package br.com.sailboat.zerotohero.view.ui.workout.insert;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.exception.EntityNotFoundException;
import br.com.sailboat.canoe.exception.RequiredFieldNotFilledException;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.canoe.helper.StringHelper;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.model.Workout;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutExerciseSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutSQLite;

public class InsertWorkoutPresenter extends BasePresenter<InsertWorkoutPresenter.View> {

    private InsertWorkoutViewModel viewModel = new InsertWorkoutViewModel();

    public InsertWorkoutPresenter(InsertWorkoutPresenter.View view) {
        super(view);
    }

    @Override
    public void extractExtrasFromIntent(Intent intent) {
        long workoutId = Extras.getWorkoutId(intent);
        getViewModel().setWorkoutId(workoutId);
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
        ArrayList array = (ArrayList) getViewModel().getExercises();
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
                WorkoutExerciseSQLite dao = new WorkoutExerciseSQLite(getContext());
                dao.deleteFromWorkout(workout.getId());

                for (Exercise e : getViewModel().getExercises()) {
                    dao.save(workout.getId(), e.getId());
                }

            }

            private void prepareAndSaveWorkout() throws Exception {
                workout = new Workout();
                workout.setId(viewModel.getWorkoutId());
                workout.setName(viewModel.getName());

                if (hasWorkoutToEdit()) {
                    new WorkoutSQLite(getContext()).update(workout);
                } else {
                    new WorkoutSQLite(getContext()).save(workout);
                }

            }

        });
    }

    private void extractInfoFromViews() {
        String name = getView().getTextFromWorkoutName();
        getViewModel().setName(name);
    }

    public void onResultOkExerciseChooser(Intent data) {
        List<Exercise> exercises = Extras.getExercises(data);
        getExercises().clear();
        getExercises().addAll(exercises);
        getView().updateExercisesListAndVisibility();
    }

    private void loadExercises() throws Exception {
        long workoutId = getViewModel().getWorkoutId();

        getExercises().clear();
        getExercises().addAll(new ExerciseSQLite(getContext()).getFromWorkout(workoutId));
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
        return getViewModel().getWorkoutId() != -1;
    }

    private void performValidations() throws Exception {
        String name = getViewModel().getName();

        if (StringHelper.isNullOrEmpty(name)) {
            throw new Exception(getString(R.string.exeption_workout_name));
        }

        if (getViewModel().getExercises().size() == 0) {
            throw new Exception(getString(R.string.exeption_selected_exercises));
        }
    }

    private void startEditingWorkout() {
        AsyncHelper.execute(new AsyncHelper.Callback() {

            @Override
            public void doInBackground() throws Exception {
                long workoutId = getViewModel().getWorkoutId();

                loadWorkoutInfo(workoutId);
                loadExercises();
            }

            @Override
            public void onSuccess() {
                getView().setActivityToHideKeyboard();
                getView().updateWorkoutNameView(getViewModel().getName());
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
        Workout workout = new WorkoutSQLite(getContext()).getWorkoutById(workoutId);
        getViewModel().setName(workout.getName());
    }

    private InsertWorkoutViewModel getViewModel() {
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
        void updateWorkoutNameView(String name);
        void updateToolbarTitle(String title);
    }

}
