package br.com.sailboat.zerotohero.view.workout.list;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.zerotohero.model.sqlite.Workout;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutSQLite;

public class WorkoutListPresenter extends BasePresenter<WorkoutListPresenter.View> {

    private WorkoutListViewModel viewModel = new WorkoutListViewModel();

    public WorkoutListPresenter(WorkoutListPresenter.View view) {
        super(view);
    }

    @Override
    protected void onResumeFirstSession() {
        loadWorkouts();
    }

    @Override
    protected void onResumeAfterRestart() {
        updateContentViews();
    }

    public void onClickNewWorkout() {
        getView().startNewWorkoutActivity();
    }

    public void onClickWorkout(int position) {
        Workout workout = getWorkouts().get(position);
        getView().startWorkoutDetailsActivity(workout.getId());
    }

    public void postActivityResult() {
        loadWorkouts();
    }

    public List<Workout> getWorkouts() {
        return viewModel.getWorkoutList();
    }

    private void loadWorkouts() {
        AsyncHelper.execute(new AsyncHelper.Callback() {

            List<Workout> workouts = new ArrayList<>();

            @Override
            public void doInBackground() throws Exception {
                workouts =  WorkoutSQLite.newInstance(getContext()).getAll();
            }

            @Override
            public void onSuccess() {
                viewModel.getWorkoutList().clear();
                viewModel.getWorkoutList().addAll(workouts);
                updateContentViews();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
                updateContentViews();
            }
        });

    }

    private void updateContentViews() {
        getView().updateWorkoutList();
        updateVisibilityOfViews();
    }

    private void updateVisibilityOfViews() {
        if (getWorkouts().isEmpty()) {
            getView().hideWorkouts();
            getView().showEmptyList();
        } else {
            getView().showWorkouts();
            getView().hideEmptyList();
        }
    }


    public interface View extends BasePresenter.View{
        void updateWorkoutList();
        void startNewWorkoutActivity();
        void startWorkoutDetailsActivity(long workoutId);
        void hideWorkouts();
        void showEmptyList();
        void showWorkouts();
        void hideEmptyList();
    }

}
