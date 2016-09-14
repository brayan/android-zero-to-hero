package com.brayanbedritchuk.zerotohero.view.exercise.list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.view.adapter.ExercisesListAdapter;
import com.brayanbedritchuk.zerotohero.view.exercise.list.presenter.ExerciseListView;
import com.brayanbedritchuk.zerotohero.view.exercise.list.presenter.ExerciseListPresenter;

public class ExerciseListFragment extends Fragment implements ExerciseListView, ExercisesListAdapter.Callback {

    private ExerciseListPresenter presenter;

    private RecyclerView recyclerView;
    private ExercisesListAdapter adapter;
    private View emptyList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_recycler, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    private void initPresenter() {
        setPresenter(new ExerciseListPresenter(this));
    }

    private void initViews(View view) {
        inflateViews(view);
        initRecyclerView();
        initVisibilityOfViews();
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
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public void onClickWorkout(int position) {
        getPresenter().onClickExercise(position);
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

    private void initVisibilityOfViews() {
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

    public ExerciseListPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(ExerciseListPresenter presenter) {
        this.presenter = presenter;
    }

}
