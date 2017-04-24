package br.com.sailboat.zerotohero.view.ui.workout.details;

import android.content.Intent;

import java.util.List;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.model.Workout;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutExerciseSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutSQLite;
import br.com.sailboat.zerotohero.view.adapter.ExercisesListAdapter;

public class WorkoutDetailsPresenter extends BasePresenter<WorkoutDetailsPresenter.View> implements ExercisesListAdapter.Callback {

    private WorkoutDetailsViewModel viewModel = new WorkoutDetailsViewModel();

    public WorkoutDetailsPresenter(WorkoutDetailsPresenter.View view) {
        super(view);
    }

    @Override
    public void extractExtrasFromIntent(Intent intent) {
        long workoutId = Extras.getWorkoutId(intent);
        getViewModel().setWorkoutId(workoutId);
    }

    @Override
    protected void postResume() {
        loadDetails();
    }

    public void onClickEditWorkout() {
        long workoutId = getViewModel().getWorkoutId();
        getView().startEditWorkoutActivity(workoutId);
    }

    public void onClickNavigation() {
        getView().closeActivityWithResultCanceled();
    }

    public void onClickMenuDelete() {
        getView().showDialogDeleteWorkout();
    }

    public void onClickDeleteWorkout() {
        AsyncHelper.execute(new AsyncHelper.Callback() {

            @Override
            public void doInBackground() throws Exception {
                long workoutId = getViewModel().getWorkoutId();
                new WorkoutSQLite(getContext()).delete(workoutId);
                new WorkoutExerciseSQLite(getContext()).deleteFromWorkout(workoutId);
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

    @Override
    public void onClickExercise(int position) {
        Exercise exercise = getExercises().get(position);
        getView().startExerciseDetailsActivity(exercise.getId());
    }

    public void postActivityResult() {
        loadDetails();
    }

    private void loadDetails() {

        AsyncHelper.execute(new AsyncHelper.Callback() {

            Workout workout;
            List<Exercise> exercises;

            @Override
            public void doInBackground() throws Exception {
                long workoutId = getViewModel().getWorkoutId();

                workout = new WorkoutSQLite(getContext()).getWorkoutById(workoutId);
                exercises = new ExerciseSQLite(getContext()).getFromWorkout(workoutId);
            }

            @Override
            public void onSuccess() {
                updateViewModel();
                updateContentViews();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
                closeActivityWithResultCanceled();
            }

            private void updateViewModel() {
                getViewModel().setWorkout(workout);
                getViewModel().getExerciseList().clear();
                getViewModel().getExerciseList().addAll(exercises);
            }

        });
    }

    private void updateContentViews() {
        updateTitle();
        getView().updateExerciseListView();
        getView().updateVisibilityOfViews();
    }

    private void updateTitle() {
        getView().setTitle(getViewModel().getWorkout().getName());
    }

    private WorkoutDetailsViewModel getViewModel() {
        return viewModel;
    }

    public List<Exercise> getExercises() {
        return getViewModel().getExerciseList();
    }


    public interface View extends BasePresenter.View {
        void updateExerciseListView();
        void setTitle(String title);
        void updateVisibilityOfViews();
        void startEditWorkoutActivity(long workoutId);
        void showDialogDeleteWorkout();
        void startExerciseDetailsActivity(long exerciseId);
    }

}
