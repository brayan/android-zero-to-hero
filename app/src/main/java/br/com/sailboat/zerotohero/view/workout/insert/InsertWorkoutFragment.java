package br.com.sailboat.zerotohero.view.workout.insert;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.helper.RequestCodeHelper;
import br.com.sailboat.zerotohero.model.view.ExerciseView;
import br.com.sailboat.zerotohero.view.adapter.ExercisesListAdapter;
import br.com.sailboat.zerotohero.view.adapter.SwipeExercise;
import br.com.sailboat.zerotohero.view.exercise.selector.ExerciseSelectorActivity;

public class InsertWorkoutFragment extends BaseFragment<InsertWorkoutPresenter> implements InsertWorkoutPresenter.View, ExercisesListAdapter.Callback {

    private Toolbar toolbar;
    private RecyclerView recycler;
    private FloatingActionButton fab;
    private EditText etWorkoutName;
    private View emptyList;

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
    protected InsertWorkoutPresenter newPresenterInstance() {
        return new InsertWorkoutPresenter(this);
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
    protected void initViews(View view) {
        initToolbar(view);
        initEditTexts(view);
        initRecyclerView(view);
        initFab(view);
        initEmptyView(view);
    }

    @Override
    public void updateExercises() {
        recycler.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void startExercisesChooserActivity(ArrayList<ExerciseView> exercises) {
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
    public void setTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void hideExercises() {
        recycler.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView() {
        emptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showExercises() {
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        emptyList.setVisibility(View.GONE);
    }

    @Override
    public void onClickExercise(int position) {
    }

    @Override
    public List<ExerciseView> getExercises() {
        return getPresenter().getExercises();
    }

    private void initRecyclerView(View view) {
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        ExercisesListAdapter adapter = new ExercisesListAdapter(this);
        recycler.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new SwipeExercise(getActivity(), adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recycler);
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
    }

    private void initEditTexts(View view) {
        etWorkoutName = (EditText) view.findViewById(R.id.frg_insert_workout__et__name);
    }

    private void initFab(View view) {
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onClickAddExercises();
            }
        });
    }

    private void initEmptyView(View view) {
        emptyList = view.findViewById(R.id.emptyList);

        ImageView imgEmpty = (ImageView) emptyList.findViewById(R.id.imgEmptyList);
        imgEmpty.setColorFilter(ContextCompat.getColor(getActivity(), R.color.md_teal_300), PorterDuff.Mode.SRC_ATOP);

        TextView tvTitle = (TextView) emptyList.findViewById(R.id.tvEmptyListTitle);
        tvTitle.setText(R.string.no_exercises);

        TextView tvMessage = (TextView) emptyList.findViewById(R.id.tvEmptyListMessage);
        tvMessage.setText(R.string.click_plus_to_add);

        emptyList.setVisibility(View.GONE);
    }

}
