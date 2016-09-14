package com.brayanbedritchuk.zerotohero.view.insert_or_edit_workout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.brayanbedritchuk.zerotohero.view.adapter.ExercisesListAdapter;
import com.brayanbedritchuk.zerotohero.view.exercise_chooser.ExerciseChooserActivity;
import com.brayanbedritchuk.zerotohero.view.insert_or_edit_workout.presenter.InsertOrEditWorkoutPresenter;
import com.brayanbedritchuk.zerotohero.view.insert_or_edit_workout.presenter.InsertOrEditWorkoutView;

import java.util.ArrayList;

public class InsertOrEditWorkoutFragment extends Fragment implements InsertOrEditWorkoutView {

    private static final int REQUEST_EXERCISE_CHOOSER = 0;

    private InsertOrEditWorkoutPresenter presenter;

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FloatingActionButton fabAddExercises;

    private View emptyList;

    private ExercisesListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        initPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_insert_or_edit_workout, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_insert_edit_workout, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_insert_edit_workout_save: {
                getPresenter().onClickMenuSave();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    private void initPresenter() {
        setPresenter(new InsertOrEditWorkoutPresenter(this));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            onActivityResultOk(requestCode, data);
        } else {
            onActivityResultCanceled(requestCode, data);
        }
    }

    private void onActivityResultOk(int requestCode, Intent data) {
        switch (requestCode) {
            case REQUEST_EXERCISE_CHOOSER: {
                getPresenter().onResultOkExerciseChooser(data);
            }
        }
    }

    private void onActivityResultCanceled(int requestCode, Intent data) {
        // TODO
    }

    private void initViews(View view) {
        inflateViews(view);
        initRecyclerView();
        initToolbar();
        initFab();
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
    public void startExercisesChooserActivity(ArrayList<Exercise> exercises) {
        ExerciseChooserActivity.start(this, exercises, REQUEST_EXERCISE_CHOOSER);
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    private void inflateViews(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        fabAddExercises = (FloatingActionButton) view.findViewById(R.id.fab);
        emptyList = view.findViewById(R.id.empty_list);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ExercisesListAdapter(getPresenter().getExercises(), null);
        recyclerView.setAdapter(adapter);
    }

    private void initToolbar() {
        initAppCompatActivity();
    }

    protected void initAppCompatActivity() {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.new_workout);
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private void initFab() {
        fabAddExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onClickAddExercises();
            }
        });
    }

    private void updateVisibilityOfViews() {
        boolean emptyList = getPresenter().getExercises().isEmpty();

        if (emptyList) {
            recyclerView.setVisibility(View.GONE);
            this.emptyList.setVisibility(View.VISIBLE);

        } else {
            this.emptyList.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

    }

    public InsertOrEditWorkoutPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(InsertOrEditWorkoutPresenter presenter) {
        this.presenter = presenter;
    }

}
