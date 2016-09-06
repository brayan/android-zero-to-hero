package com.brayanbedritchuk.zerotohero.view.workout_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.model.entity.Exercise;
import com.brayanbedritchuk.zerotohero.model.entity.Workout;
import com.brayanbedritchuk.zerotohero.view.new_workout.NewWorkoutActivity;
import com.brayanbedritchuk.zerotohero.view.workout_details.adapter.ExercisesListAdapter;
import com.brayanbedritchuk.zerotohero.view.workout_details.presenter.WorkoutDetailsPresenter;
import com.brayanbedritchuk.zerotohero.view.workout_details.presenter.WorkoutDetailsView;

public class WorkoutDetailsFragment extends Fragment implements WorkoutDetailsView, ExercisesListAdapter.Callback {

    private WorkoutDetailsPresenter presenter;

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ExercisesListAdapter adapter;
    private View emptyList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initPresenter();
        getExtrasFromIntent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_workout_details, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    private void initPresenter() {
        setPresenter(new WorkoutDetailsPresenter(this));
    }

    private void getExtrasFromIntent() {
        Intent intent = getActivity().getIntent();
        Workout workout = (Workout) intent.getSerializableExtra(WorkoutDetailsActivity.EXTRA_WORKOUT);
        getPresenter().getViewModel().setWorkout(workout);
    }

    private void initViews(View view) {
        inflateViews(view);
        initToolbar();
        initRecyclerView();
        initVisibilityOfViews();
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.workout_details);
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
        NewWorkoutActivity.start(getActivity());
    }

    @Override
    public void startExerciseDetailsActivity(Exercise exercise) {
//        WorkoutDetailsActivity.start(getActivity(), workout);
        showToast("Starting " + exercise.getName() + "...");
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public void onClickWorkout(int position) {
//        getPresenter().onClickWorkout(position);
    }

    public void onClickFab() {
        getPresenter().onClickNewWorkout();
    }

    private void inflateViews(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) view.findViewById(R.id.frag_workout_details__recycler__exercises);
        emptyList = view.findViewById(R.id.empty_list_workouts);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ExercisesListAdapter(getPresenter().getExercises(), this);
        recyclerView.setAdapter(adapter);
    }

    private void initVisibilityOfViews() {
        emptyList.setVisibility(View.GONE);
    }

    private void updateVisibilityOfViews() {
        boolean isEmpty = getPresenter().getExercises().isEmpty();

        if (isEmpty) {
            recyclerView.setVisibility(View.GONE);
            emptyList.setVisibility(View.VISIBLE);

        } else {
            emptyList.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

    }

    public WorkoutDetailsPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(WorkoutDetailsPresenter presenter) {
        this.presenter = presenter;
    }

}
