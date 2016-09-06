package com.brayanbedritchuk.zerotohero.view.new_workout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.view.new_workout.presenter.NewWorkoutPresenter;
import com.brayanbedritchuk.zerotohero.view.new_workout.presenter.NewWorkoutView;

public class NewWorkoutFragment extends Fragment implements NewWorkoutView {

    private NewWorkoutPresenter presenter;

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private View emptyList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        initPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_new_workout, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    private void initPresenter() {
        setPresenter(new NewWorkoutPresenter(this));
    }

    private void initViews(View view) {
        inflateViews(view);
        initRecyclerView();
        initToolbar();
        initFab();
    }

    @Override
    public void updateContentViews() {
//        adapter.notifyDataSetChanged();
        updateVisibilityOfViews();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    private void inflateViews(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_new_workout_list__recycler__exercises);
        emptyList = view.findViewById(R.id.empty_list_workouts);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        adapter = new NewWorkoutExercicesAdapter(getPresenter().getWorkouts());
//        recyclerView.setAdapter(adapter);
    }

    private void initToolbar() {
        initAppCompatActivity();
    }

    protected void initAppCompatActivity() {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
    }

    private void initFab() {
    }

    private void updateVisibilityOfViews() {
        boolean emptyList = getPresenter().getWorkouts().isEmpty();

        if (emptyList) {
            recyclerView.setVisibility(View.GONE);
            this.emptyList.setVisibility(View.VISIBLE);

        } else {
            this.emptyList.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

    }

    public NewWorkoutPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(NewWorkoutPresenter presenter) {
        this.presenter = presenter;
    }

}
