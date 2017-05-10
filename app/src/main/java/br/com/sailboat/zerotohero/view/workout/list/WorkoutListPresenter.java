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
    protected void postResume() {
        loadWorkouts();
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

    private void loadWorkouts() {
        AsyncHelper.execute(new AsyncHelper.Callback() {

            List<Workout> workouts = new ArrayList<>();

            @Override
            public void doInBackground() throws Exception {
                workouts =  WorkoutSQLite.newInstance(getContext()).getAll();
            }

            @Override
            public void onSuccess() {
                getViewModel().getWorkoutList().clear();
                getViewModel().getWorkoutList().addAll(workouts);
                getView().updateContentViews();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
                getView().updateContentViews();
            }
        });

    }

    private WorkoutListViewModel getViewModel() {
        return viewModel;
    }

    public List<Workout> getWorkouts() {
        return getViewModel().getWorkoutList();
    }


    public interface View extends BasePresenter.View{
        void updateContentViews();
        void startNewWorkoutActivity();
        void startWorkoutDetailsActivity(long workoutId);
    }

}
