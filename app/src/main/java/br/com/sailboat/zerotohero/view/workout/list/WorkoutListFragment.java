package br.com.sailboat.zerotohero.view.workout.list;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.view.adapter.WorkoutListAdapter;
import br.com.sailboat.zerotohero.view.workout.details.WorkoutDetailsActivity;
import br.com.sailboat.zerotohero.view.workout.insert.InsertWorkoutActivity;

public class WorkoutListFragment extends BaseFragment<WorkoutListPresenter> implements WorkoutListPresenter.View, WorkoutListAdapter.Callback {

    private RecyclerView recycler;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_workout_list;
    }

    @Override
    protected WorkoutListPresenter newPresenterInstance() {
        return new WorkoutListPresenter(this);
    }

    @Override
    protected void postActivityResult(int requestCode, Intent data) {
        getPresenter().postActivityResult();
    }

    @Override
    protected void initEmptyViewMessages() {
        setEmptyViewMessage1("No workouts");
        setEmptyViewMessage2("Create a new workout plan by tapping the + button");
    }

    @Override
    protected void initViews() {
        initRecyclerView();
    }

    @Override
    public void updateWorkoutList() {
        recycler.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void startNewWorkoutActivity() {
        InsertWorkoutActivity.start(this);
    }

    @Override
    public void startWorkoutDetailsActivity(long workoutId) {
        WorkoutDetailsActivity.start(this, workoutId);
    }

    @Override
    public void hideWorkouts() {
        recycler.setVisibility(View.GONE);
    }

    @Override
    public void showWorkouts() {
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClickWorkout(int position) {
        getPresenter().onClickWorkout(position);
    }

    public void onClickFab() {
        getPresenter().onClickNewWorkout();
    }

    private void initRecyclerView() {
        recycler = (RecyclerView) getView().findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        WorkoutListAdapter adapter = new WorkoutListAdapter(getPresenter().getWorkouts(), this);
        recycler.setAdapter(adapter);
    }

}
