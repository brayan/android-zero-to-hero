package br.com.sailboat.zerotohero.view.workout.list;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.canoe.helper.ScrollHelper;
import br.com.sailboat.canoe.view.about.AboutActivity;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.helper.AboutHelper;
import br.com.sailboat.zerotohero.view.adapter.WorkoutListAdapter;
import br.com.sailboat.zerotohero.view.exercise.list.ExerciseListActivity;
import br.com.sailboat.zerotohero.view.workout.details.WorkoutDetailsActivity;
import br.com.sailboat.zerotohero.view.workout.insert.InsertWorkoutActivity;

public class WorkoutListFragment extends BaseFragment<WorkoutListPresenter> implements WorkoutListPresenter.View, WorkoutListAdapter.Callback {

    @Override
    protected int getLayoutId() {
        return R.layout.frg_workout_list;
    }

    @Override
    protected WorkoutListPresenter newPresenterInstance() {
        return new WorkoutListPresenter(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (ExtrasHelper.wasStartedFromMenu(getActivity().getIntent())) {
            inflater.inflate(R.menu.menu_search, menu);
        } else {
            inflater.inflate(R.menu.menu_workout_list, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_exercises: {
                ExerciseListActivity.startFromMenu(this);
                return true;
            }
            case R.id.menu_about: {
                AboutActivity.start(getActivity(), AboutHelper.getAbout(getActivity()));
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    protected void initEmptyViewMessages() {
        setEmptyViewMessage1(getString(R.string.no_workouts_found));
        setEmptyViewMessage2(getString(R.string.ept_click_to_add));
    }

    @Override
    protected void onInitToolbar() {

        if (ExtrasHelper.wasStartedFromMenu(getActivity().getIntent())) {
            toolbar.setTitle(R.string.tab_workouts);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
        } else {
            toolbar.setTitle(R.string.app_name);
        }

    }

    @Override
    protected void onInitRecycler() {
        recycler.setAdapter(new WorkoutListAdapter(getPresenter().getWorkouts(), this));
    }

    @Override
    protected void onInitFab() {
        ScrollHelper.hideFabWhenScrolling(recycler, fab);
    }

    @Override
    protected void postActivityResult(int requestCode, Intent data) {
        getPresenter().postActivityResult();
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
    public void onClickWorkout(int position) {
        getPresenter().onClickWorkout(position);
    }

}
