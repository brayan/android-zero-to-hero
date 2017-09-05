package br.com.sailboat.zerotohero.view.exercise.list;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.canoe.recycler.EndlessRecyclerOnScrollListener;
import br.com.sailboat.canoe.view.info.InfoActivity;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.InfoHelper;
import br.com.sailboat.zerotohero.view.adapter.ExercisesListAdapter;
import br.com.sailboat.zerotohero.view.exercise.details.ExerciseDetailsActivity;
import br.com.sailboat.zerotohero.view.exercise.insert.InsertExerciseActivity;
import br.com.sailboat.zerotohero.view.workout.list.WorkoutListActivity;

public class ExerciseListFragment extends BaseFragment<ExerciseListPresenter> implements ExerciseListPresenter.View {

    private AppBarLayout appbar;
    private Toolbar toolbar;
    private RecyclerView recycler;

    @Override
    protected ExerciseListPresenter newPresenterInstance() {
        return new ExerciseListPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_exercise_list;
    }

    @Override
    protected void initEmptyViewMessages() {
        setEmptyViewMessage1(getString(R.string.no_exercises));
        setEmptyViewMessage2(getString(R.string.click_plus_to_add));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_exercise_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_workouts: {
                WorkoutListActivity.start(this);
                return true;
            }
            case R.id.menu_info: {
                InfoActivity.start(getActivity(), InfoHelper.getInfo(getActivity()));
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }

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

    @Override
    public void showExercises() {
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideExercises() {
        recycler.setVisibility(View.GONE);
    }

    public void onClickFab() {
        getPresenter().onClickNewExercise();
    }

    @Override
    protected void initViews() {
        appbar = (AppBarLayout) getView().findViewById(R.id.appbar);
        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);

        initRecyclerView();
    }

    private void initRecyclerView() {
        recycler = (RecyclerView) getView().findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(manager);
        ExercisesListAdapter adapter = new ExercisesListAdapter(getPresenter());
        recycler.setAdapter(adapter);
        recycler.addOnScrollListener(new EndlessRecyclerOnScrollListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {
                // TODO
                getPresenter().onLoadMoreExercises(currentPage);
//                Log.e("RECYCLER", "onLoadMore(): current_page: " + currentPage);
            }
        });

    }


}
