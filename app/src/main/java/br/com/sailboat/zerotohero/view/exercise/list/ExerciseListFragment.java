package br.com.sailboat.zerotohero.view.exercise.list;

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
import br.com.sailboat.zerotohero.view.adapter.ExercisesListAdapter;
import br.com.sailboat.zerotohero.view.exercise.details.ExerciseDetailsActivity;
import br.com.sailboat.zerotohero.view.exercise.insert.InsertExerciseActivity;
import br.com.sailboat.zerotohero.view.workout.list.WorkoutListActivity;

public class ExerciseListFragment extends BaseFragment<ExerciseListPresenter> implements ExerciseListPresenter.View {

    @Override
    protected int getLayoutId() {
        return R.layout.frg_exercise_list;
    }

    @Override
    protected ExerciseListPresenter newPresenterInstance() {
        return new ExerciseListPresenter(this);
    }

    @Override
    protected void initEmptyViewMessages() {
        setEmptyViewMessage1(getString(R.string.no_exercises_found));
        setEmptyViewMessage2(getString(R.string.ept_click_to_add));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (ExtrasHelper.wasStartedFromMenu(getActivity().getIntent())) {
            inflater.inflate(R.menu.menu_search, menu);
        } else {
            inflater.inflate(R.menu.menu_exercise_list, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_workouts: {
                WorkoutListActivity.startFromMenu(this);
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
    protected void onInitToolbar() {
        if (ExtrasHelper.wasStartedFromMenu(getActivity().getIntent())) {
            toolbar.setTitle(R.string.tab_exercises);
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
        recycler.setAdapter(new ExercisesListAdapter(getPresenter()));
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
    public void startExerciseDetailsActivity(long exerciseId) {
        ExerciseDetailsActivity.start(this, exerciseId);
    }

    @Override
    public void startNewExerciseActivity() {
        InsertExerciseActivity.start(this);
    }

    @Override
    public void updateExercises() {
        recycler.getAdapter().notifyDataSetChanged();
    }

}
