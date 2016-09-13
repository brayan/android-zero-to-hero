package com.brayanbedritchuk.zerotohero.view.exercise_chooser;

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
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.model.Workout;
import com.brayanbedritchuk.zerotohero.view.exercise_chooser.adapter.ExerciseChooserAdapter;
import com.brayanbedritchuk.zerotohero.view.exercise_chooser.presenter.ExerciseChooserPresenter;
import com.brayanbedritchuk.zerotohero.view.exercise_chooser.presenter.ExerciseChooserView;
import com.brayanbedritchuk.zerotohero.view.insert_or_edit_workout.InsertOrEditWorkoutActivity;

public class ExerciseChooserFragment extends Fragment implements ExerciseChooserView, ExerciseChooserAdapter.Callback {

    private ExerciseChooserPresenter presenter;

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ExerciseChooserAdapter adapter;
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
        View view = inflater.inflate(R.layout.frag_exercise_chooser, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    private void initPresenter() {
        setPresenter(new ExerciseChooserPresenter(this));
    }

    private void getExtrasFromIntent() {
        Intent intent = getActivity().getIntent();
        Workout workout = (Workout) intent.getSerializableExtra(ExerciseChooserActivity.EXTRA_WORKOUT);
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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public void updateExerciseListView() {
        adapter.notifyDataSetChanged();
        updateVisibilityOfViews();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startNewWorkoutActivity() {
        InsertOrEditWorkoutActivity.start(getActivity(), null);
    }

    @Override
    public void startExerciseDetailsActivity(Exercise exercise) {
//        WorkoutDetailsActivity.start(getActivity(), workout);
        showToast("Starting " + exercise.getName() + "...");
    }

    @Override
    public void updateTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void updateVisibilityOfViews() {
        boolean isEmpty = getPresenter().getExerciseList().isEmpty();

        if (isEmpty) {
            recyclerView.setVisibility(View.GONE);
            emptyList.setVisibility(View.VISIBLE);

        } else {
            emptyList.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void updateExerciseView(int position) {
        adapter.notifyItemChanged(position);
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public void onClickExercise(int position) {
        getPresenter().onClickExercise(position);
    }

    public void onClickFab() {
        getPresenter().onClickNewWorkout();
    }

    private void inflateViews(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) view.findViewById(R.id.frag_exercise_chooser__recycler__exercises);
        emptyList = view.findViewById(R.id.empty_list_workouts);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ExerciseChooserAdapter(getPresenter().getExerciseList(), getPresenter().getSelectedExercises(), this);
        recyclerView.setAdapter(adapter);
    }

    private void initVisibilityOfViews() {
        emptyList.setVisibility(View.GONE);
    }

    public ExerciseChooserPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(ExerciseChooserPresenter presenter) {
        this.presenter = presenter;
    }

}
