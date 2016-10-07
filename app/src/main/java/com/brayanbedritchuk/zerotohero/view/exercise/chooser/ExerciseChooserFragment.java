package com.brayanbedritchuk.zerotohero.view.exercise.chooser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.base.BaseFragment;
import com.brayanbedritchuk.zerotohero.helper.ExtrasHelper;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.view.adapter.ExerciseChooserAdapter;
import com.brayanbedritchuk.zerotohero.view.exercise.chooser.presenter.ExerciseChooserPresenter;
import com.brayanbedritchuk.zerotohero.view.exercise.chooser.presenter.ExerciseChooserView;

import java.util.ArrayList;
import java.util.List;

public class ExerciseChooserFragment extends BaseFragment<ExerciseChooserPresenter> implements ExerciseChooserView, ExerciseChooserAdapter.Callback {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ExerciseChooserAdapter adapter;
    private View emptyList;

    @Override
    protected int getLayoutId() {
        return R.layout.linlay_appbar_card_recycler;
    }

    @Override
    protected ExerciseChooserPresenter newPresenterInstance() {
        return new ExerciseChooserPresenter(this);
    }

    @Override
    protected boolean hasMenu() {
        return true;
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

    @Override
    protected void getExtrasFromIntent(Intent intent) {
        List<Exercise> exercises = ExtrasHelper.getExercises(intent);
        getPresenter().onReceiveSelectedExercises(exercises);
    }

    @Override
    protected void initViews(View view) {
        inflateViews(view);
        initToolbar();
        initRecyclerView();
        initEmptyView();
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
        toolbar.setTitle(R.string.app_name);
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
        ExtrasHelper.putExercises(exercises, data);
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

    private void initEmptyView() {
        ImageView imgEmpty = (ImageView) emptyList.findViewById(R.id.empty_list_image);
        imgEmpty.setColorFilter(ContextCompat.getColor(getActivity(), R.color.blue_300), PorterDuff.Mode.SRC_ATOP);

        TextView tvTitle = (TextView) emptyList.findViewById(R.id.empty_list_title);
        tvTitle.setText("No exercises");

        TextView tvMessage = (TextView) emptyList.findViewById(R.id.empty_list_message);
        tvMessage.setVisibility(View.GONE);

        emptyList.setVisibility(View.GONE);
    }

}
