package com.brayanbedritchuk.zerotohero.view.exercise.chooser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.view.adapter.ExerciseChooserAdapter;
import com.brayanbedritchuk.zerotohero.view.exercise.chooser.presenter.ExerciseChooserPresenter;
import com.brayanbedritchuk.zerotohero.view.exercise.chooser.presenter.ExerciseChooserView;
import com.brayanbedritchuk.zerotohero.view.workout.insert_or_edit.InsertOrEditWorkoutActivity;

import java.util.ArrayList;

public class ExerciseChooserFragment extends Fragment implements ExerciseChooserView, ExerciseChooserAdapter.Callback {

    public static final String EXTRA_SELECTED_EXERCISES = "EXTRA_SELECTED_EXERCISES";

    private ExerciseChooserPresenter presenter;

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ExerciseChooserAdapter adapter;
    private View emptyList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        initPresenter();
        getExtrasFromIntent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.linlay_appbar_card_recycler, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_exercise_chooser, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save: {
                getPresenter().onClickMenuSave();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }

    }

    private void initPresenter() {
        setPresenter(new ExerciseChooserPresenter(this));
    }

    private void getExtrasFromIntent() {
        Intent intent = getActivity().getIntent();
        ArrayList<Exercise> exercises = (ArrayList<Exercise>) intent.getSerializableExtra(ExerciseChooserActivity.EXTRA_SELECTED_EXERCISES);
        getPresenter().onReceiveSelectedExercises(exercises);
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
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().onClickNavigationIcon();
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
    public void closeActivityResultCanceled() {
        getActivity().setResult(Activity.RESULT_CANCELED);
        getActivity().finish();
    }

    @Override
    public void closeActivityResultOk(ArrayList<Exercise> exercises) {
        Intent data = new Intent();
        data.putExtra(EXTRA_SELECTED_EXERCISES, exercises);
        getActivity().setResult(Activity.RESULT_OK, data);
        getActivity().finish();
    }

    @Override
    public void updateMenu() {
        getActivity().invalidateOptionsMenu();
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
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        emptyList = view.findViewById(R.id.empty_list);
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
