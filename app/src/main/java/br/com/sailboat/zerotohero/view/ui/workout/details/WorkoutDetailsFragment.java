package br.com.sailboat.zerotohero.view.ui.workout.details;

import android.app.Activity;
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

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.canoe.dialog.TwoOptionsDialog;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.view.adapter.ExercisesListAdapter;
import br.com.sailboat.zerotohero.view.ui.exercise.details.ExerciseDetailsActivity;
import br.com.sailboat.zerotohero.view.ui.workout.insert.InsertWorkoutActivity;

public class WorkoutDetailsFragment extends BaseFragment<WorkoutDetailsPresenter> implements WorkoutDetailsPresenter.View {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private View emptyList;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_workout_details;
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
    public void setTitle(String title) {
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
    public void startEditWorkoutActivity(long workoutId) {
        InsertWorkoutActivity.start(this, workoutId);
    }

    @Override
    public void showDialogDeleteWorkout() {
        TwoOptionsDialog dialog = new TwoOptionsDialog();
        dialog.setMessage(getString(R.string.delete_workout));
        dialog.setPositiveMsg(getString(R.string.delete));
        dialog.setPositiveCallback(new TwoOptionsDialog.PositiveCallback() {
            @Override
            public void onClickPositiveOption() {
                getPresenter().onClickDeleteWorkout();
            }
        });
        dialog.show(getFragmentManager(), "DELETE_DIALOG");
    }

    @Override
    public void startExerciseDetailsActivity(long exerciseId) {
        ExerciseDetailsActivity.start(this, exerciseId);
    }

    @Override
    protected void postActivityResult(int requestCode, Intent data) {
        getPresenter().postActivityResult();
    }

    @Override
    public void closeActivityWithResultCanceled() {
        getActivity().setResult(Activity.RESULT_CANCELED);
        getActivity().finish();
    }

    private void inflateViews(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        emptyList = view.findViewById(R.id.emptyList);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ExercisesListAdapter adapter = new ExercisesListAdapter(getPresenter().getExercises(), getPresenter());
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
