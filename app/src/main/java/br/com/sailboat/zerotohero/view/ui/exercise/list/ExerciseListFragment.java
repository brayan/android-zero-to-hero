package br.com.sailboat.zerotohero.view.ui.exercise.list;

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

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.canoe.dialog.MessageDialog;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.RequestCodes;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.view.adapter.ExercisesListAdapter;
import br.com.sailboat.zerotohero.view.ui.exercise.details.ExerciseDetailsActivity;
import br.com.sailboat.zerotohero.view.ui.exercise.insert.InsertExerciseActivity;

public class ExerciseListFragment extends BaseFragment<ExerciseListPresenter> implements ExerciseListPresenter.View, ExercisesListAdapter.Callback {

    private RecyclerView recycler;
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
            case RequestCodes.INSERT_EXERCISE: {
                getPresenter().onActivityResultOkInsertOrEditExercise(data);
                return;
            }
            case RequestCodes.EXERCISE_DETAILS: {
                getPresenter().onActivityResultOkExerciseDetails(data);
                return;
            }
        }
    }

    @Override
    protected void onActivityResultCanceled(int requestCode, Intent data) {
        switch (requestCode) {
            case RequestCodes.EXERCISE_DETAILS: {
                getPresenter().onActivityResultCanceledExerciseDetails();
                return;
            }
        }
    }

    @Override
    public void updateContentViews() {
        recycler.getAdapter().notifyDataSetChanged();
        updateVisibilityOfViews();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startExerciseDetailsActivity(Exercise exercise) {
        ExerciseDetailsActivity.start(this, exercise);
    }

    @Override
    public void startNewExerciseActivity() {
        InsertExerciseActivity.start(this);
    }

    @Override
    public void showDialog(String message) {
        MessageDialog.showMessage(getFragmentManager(), message, null);
    }

    @Override
    public void updateExerciseRemoved(int position) {
        recycler.getAdapter().notifyItemRemoved(position);
    }

    public void onClickFab() {
        getPresenter().onClickNewExercise();
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
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        emptyList = view.findViewById(R.id.emptyList);
    }

    private void initRecyclerView() {
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        ExercisesListAdapter adapter = new ExercisesListAdapter(getPresenter().getExercises(), this);
        recycler.setAdapter(adapter);
    }

    private void initEmptyView() {
        ImageView imgEmpty = (ImageView) emptyList.findViewById(R.id.imgEmptyList);
        imgEmpty.setColorFilter(ContextCompat.getColor(getActivity(), R.color.md_blue_300), PorterDuff.Mode.SRC_ATOP);

        TextView tvTitle = (TextView) emptyList.findViewById(R.id.tvEmptyListTitle);
        tvTitle.setText("No exercises");

        TextView tvMessage = (TextView) emptyList.findViewById(R.id.tvEmptyListMessage);
        tvMessage.setText("Create a new exercise by tapping the + button");

        emptyList.setVisibility(View.GONE);
    }

    private void updateVisibilityOfViews() {
        boolean emptyList = getPresenter().getExercises().isEmpty();

        if (emptyList) {
            recycler.setVisibility(View.GONE);
            this.emptyList.setVisibility(View.VISIBLE);

        } else {
            this.emptyList.setVisibility(View.GONE);
            recycler.setVisibility(View.VISIBLE);
        }

    }

}
