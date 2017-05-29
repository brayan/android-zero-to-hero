package br.com.sailboat.zerotohero.view.exercise.list;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.view.adapter.ExercisesListAdapter;
import br.com.sailboat.zerotohero.view.exercise.details.ExerciseDetailsActivity;
import br.com.sailboat.zerotohero.view.exercise.insert.InsertExerciseActivity;

public class ExerciseListFragment extends BaseFragment<ExerciseListPresenter> implements ExerciseListPresenter.View {

    private RecyclerView recycler;
    private View emptyList;

    @Override
    protected ExerciseListPresenter newPresenterInstance() {
        return new ExerciseListPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_exercise_list;
    }

    @Override
    protected void postActivityResult(int requestCode, Intent data) {
        getPresenter().postActivityResult();
    }

    @Override
    public void startExerciseDetailsActivity(long exerciseId) {
        ExerciseDetailsActivity.start(this, exerciseId);
    }

    @Override
    public void startNewExerciseActivity() {
        InsertExerciseActivity.start(this);
    }

    @Override
    public void updateExercises() {
        recycler.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showExercises() {
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideExercises() {
        recycler.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyState() {
        emptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyState() {
        emptyList.setVisibility(View.GONE);
    }

    public void onClickFab() {
        getPresenter().onClickNewExercise();
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
        ExercisesListAdapter adapter = new ExercisesListAdapter(getPresenter());
        recycler.setAdapter(adapter);
    }

    private void initEmptyView() {
        ImageView imgEmpty = (ImageView) emptyList.findViewById(R.id.imgEmptyList);
        imgEmpty.setColorFilter(ContextCompat.getColor(getActivity(), R.color.md_blue_300), PorterDuff.Mode.SRC_ATOP);

        TextView tvTitle = (TextView) emptyList.findViewById(R.id.tvEmptyListTitle);
        tvTitle.setText(R.string.no_exercises);

        TextView tvMessage = (TextView) emptyList.findViewById(R.id.tvEmptyListMessage);
        tvMessage.setText(R.string.click_plus_to_add);

        emptyList.setVisibility(View.GONE);
    }

}
