package br.com.sailboat.zerotohero.view.workout.details;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.canoe.recycler.RecyclerItem;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.interactor.LoadWorkoutDetails;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.persistence.DatabaseOpenHelper;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutExerciseSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutSQLite;

public class WorkoutDetailsPresenter extends BasePresenter<WorkoutDetailsPresenter.View> {

    private WorkoutDetailsViewModel viewModel = new WorkoutDetailsViewModel();

    public WorkoutDetailsPresenter(WorkoutDetailsPresenter.View view) {
        super(view);
    }

    @Override
    public void extractExtrasFromArguments(Bundle arguments) {
        long workoutId = ExtrasHelper.getWorkoutId(arguments);
        viewModel.setWorkoutId(workoutId);
    }

    @Override
    protected void onResumeFirstSession() {
        loadDetails();
    }

    @Override
    protected void onResumeAfterRestart() {
        view.updateRecycler();
    }

    @Override
    public void onClickFab() {
        long workoutId = viewModel.getWorkoutId();
        getView().startEditWorkoutActivity(workoutId);
    }

    public void onClickMenuDelete() {
        getView().showDialogDeleteWorkout();
    }

    public void onClickDeleteWorkout() {
        AsyncHelper.execute(new AsyncHelper.Callback() {

            @Override
            public void doInBackground() throws Exception {
                long workoutId = viewModel.getWorkoutId();
                new WorkoutSQLite(DatabaseOpenHelper.getInstance(getContext())).delete(workoutId);
                new WorkoutExerciseSQLite(DatabaseOpenHelper.getInstance(getContext())).deleteFromWorkout(workoutId);
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

    public void onClickExercise(int position) {
        Exercise exercise = (Exercise) getRecyclerItemList().get(position);
        getView().startExerciseDetailsActivity(exercise.getId());
    }

    public void postActivityResult() {
        loadDetails();
    }

    private void loadDetails() {

        AsyncHelper.execute(new AsyncHelper.Callback() {

            List<RecyclerItem> recyclerItemList = new ArrayList<>();

            @Override
            public void doInBackground() throws Exception {
                recyclerItemList = LoadWorkoutDetails.loadWorkoutDetails(getContext(), viewModel.getWorkoutId());
            }

            @Override
            public void onSuccess() {
                updateViewModel();
                view.updateRecycler();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
                closeActivityWithResultCanceled();
            }

            private void updateViewModel() {
                viewModel.getRecyclerItemList().clear();
                viewModel.getRecyclerItemList().addAll(recyclerItemList);
            }

        });
    }

    public List<RecyclerItem> getRecyclerItemList() {
        return viewModel.getRecyclerItemList();
    }


    public interface View extends BasePresenter.View {
        void startEditWorkoutActivity(long workoutId);
        void showDialogDeleteWorkout();
        void startExerciseDetailsActivity(long exerciseId);
    }

}
