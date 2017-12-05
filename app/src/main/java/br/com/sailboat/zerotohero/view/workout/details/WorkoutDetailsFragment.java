package br.com.sailboat.zerotohero.view.workout.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.canoe.dialog.TwoOptionsDialog;
import br.com.sailboat.canoe.recycler.RecyclerItem;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.view.adapter.WorkoutDetailsAdapter;
import br.com.sailboat.zerotohero.view.exercise.details.ExerciseDetailsActivity;
import br.com.sailboat.zerotohero.view.workout.insert.InsertWorkoutActivity;

public class WorkoutDetailsFragment extends BaseFragment<WorkoutDetailsPresenter> implements WorkoutDetailsPresenter.View, WorkoutDetailsAdapter.Callback {


    public static WorkoutDetailsFragment newInstance(long workoutId) {
        Bundle args = new Bundle();
        ExtrasHelper.putWorkoutId(workoutId, args);
        WorkoutDetailsFragment fragment = new WorkoutDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.frg_workout_details;
    }

    @Override
    protected WorkoutDetailsPresenter newPresenterInstance() {
        return new WorkoutDetailsPresenter(this);
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
    protected void onInitToolbar() {
        toolbar.setTitle(R.string.workout_details);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    protected void onInitRecycler() {
        recycler.setAdapter(new WorkoutDetailsAdapter(this));
    }

    @Override
    protected void onInitFab() {
        fab.setImageResource(R.drawable.ic_edit_white_24dp);
    }

    @Override
    public void startEditWorkoutActivity(long workoutId) {
        InsertWorkoutActivity.start(this, workoutId);
    }

    @Override
    public void showDialogDeleteWorkout() {
        TwoOptionsDialog dialog = new TwoOptionsDialog();
        dialog.setMessage(getString(R.string.are_you_sure));
        dialog.setPositiveMsg(getString(R.string.delete));
        dialog.setPositiveCallback(new TwoOptionsDialog.PositiveCallback() {
            @Override
            public void onClickPositiveOption() {
                getPresenter().onClickDeleteWorkout();
            }
        });
        dialog.show(getFragmentManager(), TwoOptionsDialog.class.getName());
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

    @Override
    public void onClickExercise(int position) {
        presenter.onClickExercise(position);
    }

    @Override
    public List<RecyclerItem> getRecyclerItemList() {
        return presenter.getRecyclerItemList();
    }

}
