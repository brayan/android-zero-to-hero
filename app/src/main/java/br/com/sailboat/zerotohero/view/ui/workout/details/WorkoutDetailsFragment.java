package br.com.sailboat.zerotohero.view.ui.workout.details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.base.BaseFragment;
import br.com.sailboat.zerotohero.helper.DialogHelper;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.model.Workout;
import br.com.sailboat.zerotohero.view.adapter.ExercisesListAdapter;
import br.com.sailboat.zerotohero.view.ui.workout.details.presenter.WorkoutDetailsPresenter;
import br.com.sailboat.zerotohero.view.ui.workout.insert_or_edit.InsertOrEditWorkoutActivity;

public class WorkoutDetailsFragment extends BaseFragment<WorkoutDetailsPresenter> implements WorkoutDetailsPresenter.View, ExercisesListAdapter.Callback {

    private static final int REQUEST_EDIT_WORKOUT = 0;

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private View emptyList;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_workout_details;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_details, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_delete: {
                getPresenter().onClickMenuDelete();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    protected WorkoutDetailsPresenter newPresenterInstance() {
        return new WorkoutDetailsPresenter(this);
    }

    @Override
    protected void onActivityResultOk(int requestCode, Intent data) {
        switch (requestCode) {
            case REQUEST_EDIT_WORKOUT: {
                getPresenter().onResultOkEditWorkout(data);
                return;
            }
        }
    }

    @Override
    protected void initViews(View view) {
        inflateViews(view);
        initToolbar();
        initRecyclerView();
        initFab();
        initVisibilityOfViews();
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().onClickNavigation();
            }
        });
    }

    @Override
    public void updateExerciseListView() {
        recyclerView.getAdapter().notifyDataSetChanged();
        updateVisibilityOfViews();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startNewWorkoutActivity() {
//        InsertOrEditWorkoutActivity.start(getActivity(), null);
    }

    @Override
    public void startExerciseDetailsActivity(Exercise exercise) {
//        ExerciseDetailsActivity.start(getActivity(), workout);
        showToast("Starting " + exercise.getName() + "...");
    }

    @Override
    public void updateTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void updateVisibilityOfViews() {
        boolean isEmpty = getPresenter().getExercises().isEmpty();

        if (isEmpty) {
            recyclerView.setVisibility(View.GONE);
            emptyList.setVisibility(View.VISIBLE);

        } else {
            emptyList.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void startEditWorkoutActivity(Workout workout) {
        InsertOrEditWorkoutActivity.start(this, workout, REQUEST_EDIT_WORKOUT);
    }

    @Override
    public void closeActivityWithResultCanceled() {
        getActivity().setResult(Activity.RESULT_CANCELED);
        getActivity().finish();
    }

    @Override
    public void closeActivityWithResultOkAndDeleteWorkout(Workout workout) {
        Intent intent = new Intent();
        ExtrasHelper.putWorkout(workout, intent);
        ExtrasHelper.deleteWorkout(intent);

        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public void onClickExercise(int position) {
//        getPresenter().onClickExercise(position);
        DialogHelper.showMessage(getFragmentManager(), "Aeeee");
    }

    public void onClickFab() {
        getPresenter().onClickNewWorkout();
    }

    private void inflateViews(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        emptyList = view.findViewById(R.id.empty_list);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ExercisesListAdapter adapter = new ExercisesListAdapter(getPresenter().getExercises(), null);
        recyclerView.setAdapter(adapter);
    }

    private void initVisibilityOfViews() {
        emptyList.setVisibility(View.GONE);
    }

    private void initFab() {
        fab.setImageResource(R.drawable.ic_edit_white_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onClickEditWorkout();
            }
        });
    }

}
