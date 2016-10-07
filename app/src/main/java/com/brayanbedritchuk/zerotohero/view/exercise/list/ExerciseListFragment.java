package com.brayanbedritchuk.zerotohero.view.exercise.list;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.base.BaseFragment;
import com.brayanbedritchuk.zerotohero.helper.DialogHelper;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.view.adapter.ExercisesListAdapter;
import com.brayanbedritchuk.zerotohero.view.exercise.details.ExerciseDetailsActivity;
import com.brayanbedritchuk.zerotohero.view.exercise.insert_or_edit.InsertOrEditExerciseActivity;
import com.brayanbedritchuk.zerotohero.view.exercise.list.presenter.ExerciseListPresenter;
import com.brayanbedritchuk.zerotohero.view.exercise.list.presenter.ExerciseListView;

public class ExerciseListFragment extends BaseFragment<ExerciseListPresenter> implements ExerciseListView, ExercisesListAdapter.Callback {

    private static final int REQUEST_NEW_EXERCISE = 0;
    private static final int REQUEST_DETAILS = 1;

    private RecyclerView recyclerView;
    private ExercisesListAdapter adapter;
    private View emptyList;

    @Override
    protected ExerciseListPresenter newPresenterInstance() {
        return new ExerciseListPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frame_recycler;
    }

    @Override
    protected void onActivityResultOk(int requestCode, Intent data) {
        switch (requestCode) {
            case REQUEST_NEW_EXERCISE: {
                getPresenter().onActivityResultOkInsertOrEditExercise(data);
                return;
            }
            case REQUEST_DETAILS: {
                getPresenter().onActivityResultExerciseDetails(data);
                return;
            }
        }
    }

    @Override
    protected void onActivityResultCanceled(int requestCode, Intent data) {
        switch (requestCode) {
            case REQUEST_DETAILS: {
                getPresenter().onResultCanceledExerciseDetails();
                return;
            }
        }
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
    public void startExerciseDetailsActivity(Exercise exercise) {
        ExerciseDetailsActivity.start(this, exercise, REQUEST_DETAILS);
    }

    @Override
    public void startNewExerciseActivity() {
        InsertOrEditExerciseActivity.start(this, REQUEST_NEW_EXERCISE);
    }

    @Override
    public void showDialog(String message) {
        DialogHelper.showMessage(getFragmentManager(), message);
    }

    @Override
    public void updateExerciseRemoved(int position) {
        adapter.notifyItemRemoved(position);
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public void onClickExercise(int position) {
        getPresenter().onClickExercise(position);
    }

    @Override
    protected void initViews(View view) {
        inflateViews(view);
        initRecyclerView();
        initEmptyView();
    }

    private void inflateViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        emptyList = view.findViewById(R.id.empty_list);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ExercisesListAdapter(getPresenter().getExercises(), this);
        recyclerView.setAdapter(adapter);
    }

    private void initEmptyView() {
        ImageView imgEmpty = (ImageView) emptyList.findViewById(R.id.empty_list_image);
        imgEmpty.setColorFilter(ContextCompat.getColor(getActivity(), R.color.blue_300), PorterDuff.Mode.SRC_ATOP);

        TextView tvTitle = (TextView) emptyList.findViewById(R.id.empty_list_title);
        tvTitle.setText("No exercises");

        TextView tvMessage = (TextView) emptyList.findViewById(R.id.empty_list_message);
        tvMessage.setText("Create a new exercise by tapping the + button");

        emptyList.setVisibility(View.GONE);
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

    public void onClickFab() {
        getPresenter().onClickNewExercise();
    }
}
