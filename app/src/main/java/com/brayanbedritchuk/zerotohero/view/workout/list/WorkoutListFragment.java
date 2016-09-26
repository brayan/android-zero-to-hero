package com.brayanbedritchuk.zerotohero.view.workout.list;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.base.BaseFragment;
import com.brayanbedritchuk.zerotohero.model.Workout;
import com.brayanbedritchuk.zerotohero.view.adapter.WorkoutListAdapter;
import com.brayanbedritchuk.zerotohero.view.workout.details.WorkoutDetailsActivity;
import com.brayanbedritchuk.zerotohero.view.workout.insert_or_edit.InsertOrEditWorkoutActivity;
import com.brayanbedritchuk.zerotohero.view.workout.list.presenter.WorkoutListPresenter;
import com.brayanbedritchuk.zerotohero.view.workout.list.presenter.WorkoutListView;

public class WorkoutListFragment extends BaseFragment<WorkoutListPresenter> implements WorkoutListView, WorkoutListAdapter.Callback {

    private static final int REQUEST_NEW_WORKOUT = 0;
    private static final int REQUEST_DETAILS = 1;

    private RecyclerView recyclerView;
    private WorkoutListAdapter adapter;
    private View emptyList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_recycler, container, false);
        initViews(view);
        return view;
    }

    @Override
    protected WorkoutListPresenter newPresenterInstance() {
        return new WorkoutListPresenter(this);
    }

    @Override
    protected void onActivityResultOk(int requestCode, Intent data) {
        switch (requestCode) {
            case REQUEST_NEW_WORKOUT: {
                getPresenter().onActivityResultOkInsertOrEditWorkout(data);
                return;
            }
            case REQUEST_DETAILS: {
                getPresenter().onActivityResultOkWorkoutDetails(data);
                return;
            }
        }

    }

    @Override
    protected void onActivityResultCanceled(int requestCode, Intent data) {
        switch (requestCode) {
            case REQUEST_DETAILS: {
                getPresenter().onResultCanceledWorkoutDetails();
                return;
            }
        }
    }

    private void initViews(View view) {
        inflateViews(view);
        initRecyclerView();
        initEmptyView();
    }

    @Override
    public void updateContentViews() {
        adapter.notifyDataSetChanged();
        updateVisibilityOfViews();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startNewWorkoutActivity() {
        InsertOrEditWorkoutActivity.start(this, REQUEST_NEW_WORKOUT);
    }

    @Override
    public void startWorkoutDetailsActivity(Workout workout) {
        WorkoutDetailsActivity.start(this, workout, REQUEST_DETAILS);
    }

    @Override
    public void startWorkoutDetailsActivityWithAnimation(Workout workout) {
        startWorkoutDetailsActivity(workout);
    }

    @Override
    public void updateWorkoutRemoved(int position) {
        adapter.notifyItemRemoved(position);
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public void onClickWorkout(int position) {
        getPresenter().onClickWorkout(position);
    }

    public void onClickFab() {
        getPresenter().onClickNewWorkout();
    }

    private void inflateViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        emptyList = view.findViewById(R.id.empty_list);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new WorkoutListAdapter(getPresenter().getWorkouts(), this);
        recyclerView.setAdapter(adapter);
    }

    private void initEmptyView() {
        ImageView imgEmpty = (ImageView) emptyList.findViewById(R.id.empty_list_image);
        imgEmpty.setColorFilter(ContextCompat.getColor(getActivity(), R.color.orange_300), PorterDuff.Mode.SRC_ATOP);

        TextView tvTitle = (TextView) emptyList.findViewById(R.id.empty_list_title);
        tvTitle.setText("No workouts");

        TextView tvMessage = (TextView) emptyList.findViewById(R.id.empty_list_message);
        tvMessage.setText("Create a new workout plan by tapping the + button");

        emptyList.setVisibility(View.GONE);
    }

    private void updateVisibilityOfViews() {
        boolean emptyList = getPresenter().getWorkouts().isEmpty();

        if (emptyList) {
            recyclerView.setVisibility(View.GONE);
            this.emptyList.setVisibility(View.VISIBLE);

        } else {
            this.emptyList.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

    }

}
