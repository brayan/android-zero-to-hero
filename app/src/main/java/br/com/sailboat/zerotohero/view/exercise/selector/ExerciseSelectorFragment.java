package br.com.sailboat.zerotohero.view.exercise.selector;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.List;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.model.view.ExerciseView;
import br.com.sailboat.zerotohero.view.adapter.ExerciseChooserAdapter;

public class ExerciseSelectorFragment extends BaseFragment<ExerciseSelectorPresenter> implements ExerciseSelectorPresenter.View, ExerciseChooserAdapter.Callback {

    private Toolbar toolbar;
    private RecyclerView recycler;
    private View emptyList;
    private FloatingActionButton fab;

    public static ExerciseSelectorFragment newInstance(List<ExerciseView> exercises) {
        Bundle args = new Bundle();
        ExtrasHelper.putExerciseViewList(exercises, args);
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
    protected void initViews(View view) {
        initToolbar(view);
        initRecyclerView(view);
        initEmptyView(view);
        initFab(view);
    }

    @Override
    public void updateExerciseList() {
        recycler.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void updateExerciseView(int position) {
        recycler.getAdapter().notifyItemChanged(position);
    }

    @Override
    public void closeActivityResultOk(List<ExerciseView> exercises) {
        Intent data = new Intent();
        ExtrasHelper.putExerciseViewList(exercises, data);
        getActivity().setResult(Activity.RESULT_OK, data);
        getActivity().finish();
    }

    @Override
    public void hideRecycler() {
        recycler.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView() {
        emptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRecycler() {
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        emptyList.setVisibility(View.GONE);
    }

    @Override
    public LinkedHashMap<Long, ExerciseView> getSelectedExercises() {
        return getPresenter().getSelectedExercises();
    }

    @Override
    public List<ExerciseView> getExerciseList() {
        return getPresenter().getExerciseList();
    }

    @Override
    public void onClickExercise(int position) {
        getPresenter().onClickExercise(position);
    }

    @Override
    public boolean isExerciseSelected(ExerciseView exercise) {
        return getPresenter().isExerciseSelected(exercise);
    }

    private void initToolbar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        toolbar.setTitle(R.string.app_name);
    }

    private void initRecyclerView(View view) {
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        ExerciseChooserAdapter adapter = new ExerciseChooserAdapter(this);
        recycler.setAdapter(adapter);
    }

    private void initEmptyView(View view) {
        emptyList = view.findViewById(R.id.emptyList);

        ImageView imgEmpty = (ImageView) emptyList.findViewById(R.id.imgEmptyList);
        imgEmpty.setColorFilter(ContextCompat.getColor(getActivity(), R.color.md_blue_300), PorterDuff.Mode.SRC_ATOP);

        TextView tvTitle = (TextView) emptyList.findViewById(R.id.tvEmptyListTitle);
        tvTitle.setText(R.string.no_exercises);

        TextView tvMessage = (TextView) emptyList.findViewById(R.id.tvEmptyListMessage);
        tvMessage.setVisibility(View.GONE);

        emptyList.setVisibility(View.GONE);
    }

    private void initFab(View view) {
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_check_white_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().onClickFabSave();
            }
        });
    }

}
