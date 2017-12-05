package br.com.sailboat.zerotohero.view.workout.insert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.helper.RequestCodeHelper;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.view.adapter.ExercisesListAdapter;
import br.com.sailboat.zerotohero.view.adapter.ItemTouchHelperAdapter;
import br.com.sailboat.zerotohero.view.adapter.SwipeExercise;
import br.com.sailboat.zerotohero.view.exercise.selector.ExerciseSelectorActivity;

public class InsertWorkoutFragment extends BaseFragment<InsertWorkoutPresenter> implements InsertWorkoutPresenter.View, ExercisesListAdapter.Callback {

    private EditText etWorkoutName;


    public static InsertWorkoutFragment newInstance(long workoutId) {
        Bundle args = new Bundle();
        ExtrasHelper.putWorkoutId(workoutId, args);
        InsertWorkoutFragment fragment = new InsertWorkoutFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.frg_insert_workout;
    }

    @Override
    protected InsertWorkoutPresenter newPresenterInstance() {
        return new InsertWorkoutPresenter(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_insert_edit, menu);
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

    @Override
    protected void initEmptyViewMessages() {
        setEmptyViewMessage1(getString(R.string.no_exercises_found));
        setEmptyViewMessage2(getString(R.string.ept_click_to_add));
    }

    @Override
    protected void onInitToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    protected void onInitRecycler() {
        recycler.setAdapter(new ExercisesListAdapter(this));
        ItemTouchHelper.Callback callback = new SwipeExercise(getActivity(), (ItemTouchHelperAdapter) recycler.getAdapter());
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recycler);
    }

    @Override
    protected void initViews() {
        etWorkoutName = (EditText) getView().findViewById(R.id.frg_insert_workout__et__name);
    }

    @Override
    protected void onActivityResultOk(int requestCode, Intent data) {
        switch (requestCode) {
            case RequestCodeHelper.EXERCISE_SELECTOR: {
                getPresenter().onResultOkExerciseChooser(data);
            }
        }
    }

    @Override
    public void startExercisesChooserActivity(ArrayList<Exercise> exercises) {
        ExerciseSelectorActivity.start(this, exercises);
    }

    @Override
    public String getTextFromWorkoutName() {
        return etWorkoutName.getText().toString().trim();
    }

    @Override
    public void setWorkoutName(String name) {
        etWorkoutName.setText(name);
        etWorkoutName.setSelection(etWorkoutName.getText().length());
    }

    @Override
    public void onClickExercise(int position) {
    }

    @Override
    public List<Exercise> getExercises() {
        return getPresenter().getExercises();
    }

    @Override
    public void onItemDismiss() {
        presenter.onItemDismiss();
    }

}
