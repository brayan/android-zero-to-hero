package br.com.sailboat.zerotohero.view.workout.list;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.view.adapter.WorkoutListAdapter;
import br.com.sailboat.zerotohero.view.workout.details.WorkoutDetailsActivity;
import br.com.sailboat.zerotohero.view.workout.insert.InsertWorkoutActivity;

public class WorkoutListFragment extends BaseFragment<WorkoutListPresenter> implements WorkoutListPresenter.View, WorkoutListAdapter.Callback {

    private RecyclerView recycler;
    private View emptyList;

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
    protected void initViews(View view) {
        initRecyclerView(view);
        initEmptyView(view);
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
    public void showEmptyList() {
        emptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showWorkouts() {
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyList() {
        emptyList.setVisibility(View.GONE);
    }

    @Override
    public void onClickWorkout(int position) {
        getPresenter().onClickWorkout(position);
    }

    public void onClickFab() {
        getPresenter().onClickNewWorkout();
    }

    private void initRecyclerView(View view) {
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        WorkoutListAdapter adapter = new WorkoutListAdapter(getPresenter().getWorkouts(), this);
        recycler.setAdapter(adapter);
    }

    private void initEmptyView(View view) {
        emptyList = view.findViewById(R.id.emptyList);

        ImageView imgEmpty = (ImageView) emptyList.findViewById(R.id.imgEmptyList);
        imgEmpty.setColorFilter(ContextCompat.getColor(getActivity(), R.color.md_orange_300), PorterDuff.Mode.SRC_ATOP);

        TextView tvTitle = (TextView) emptyList.findViewById(R.id.tvEmptyListTitle);
        tvTitle.setText("No workouts");

        TextView tvMessage = (TextView) emptyList.findViewById(R.id.tvEmptyListMessage);
        tvMessage.setText("Create a new workout plan by tapping the + button");

        emptyList.setVisibility(View.GONE);
    }

}
