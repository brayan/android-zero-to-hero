package br.com.sailboat.zerotohero.view.exercise.selector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import java.util.List;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.view.adapter.ExerciseSelectorAdapter;

public class ExerciseSelectorFragment extends BaseFragment<ExerciseSelectorPresenter> implements ExerciseSelectorPresenter.View, ExerciseSelectorAdapter.Callback {


    public static ExerciseSelectorFragment newInstance(List<Exercise> exercises) {
        Bundle args = new Bundle();
        ExtrasHelper.putExercises(exercises, args);
        ExerciseSelectorFragment fragment = new ExerciseSelectorFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.frg_exercise_selector;
    }

    @Override
    protected ExerciseSelectorPresenter newPresenterInstance() {
        return new ExerciseSelectorPresenter(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_exercise_selector, menu);
        super.onCreateOptionsMenu(menu, inflater);
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
        toolbar.setTitle(R.string.app_name);
    }

    @Override
    protected void onInitRecycler() {
        recycler.setAdapter(new ExerciseSelectorAdapter(this));
    }

    @Override
    protected void onInitFab() {
        fab.setImageResource(R.drawable.ic_check_white_24dp);
    }

    @Override
    public void closeActivityResultOk(List<Exercise> exercises) {
        Intent data = new Intent();
        ExtrasHelper.putExercises(exercises, data);
        getActivity().setResult(Activity.RESULT_OK, data);
        getActivity().finish();
    }

    @Override
    public List<Exercise> getExerciseList() {
        return getPresenter().getExerciseList();
    }

    @Override
    public void onClickExercise(int position) {
        getPresenter().onClickExercise(position);
    }

    @Override
    public boolean isExerciseSelected(Exercise exercise) {
        return getPresenter().isExerciseSelected(exercise);
    }

}
