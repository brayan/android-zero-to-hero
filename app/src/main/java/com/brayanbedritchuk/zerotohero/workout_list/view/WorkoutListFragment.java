package com.brayanbedritchuk.zerotohero.workout_list.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.new_workout.view.NewWorkoutActivity;
import com.brayanbedritchuk.zerotohero.workout_list.presenter.WorkoutListPresenter;
import com.brayanbedritchuk.zerotohero.workout_list.presenter.WorkoutListView;
import com.brayanbedritchuk.zerotohero.workout_list.view.adapter.WorkoutListAdapter;

public class WorkoutListFragment extends Fragment implements WorkoutListView {

    private WorkoutListPresenter presenter;

    private FloatingActionButton btNewWorkout;
    private RecyclerView recyclerView;
    private WorkoutListAdapter adapter;
    private LinearLayout linlayEmptyList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        initPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_list, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    private void initPresenter() {
        setPresenter(new WorkoutListPresenter(this));
    }

    private void initViews(View view) {
        inflateViews(view);
        initRecyclerView();
        initFab();
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
    public void startNewWorkoutActivity() {
        Intent intent = new Intent(getActivity(), NewWorkoutActivity.class);
        startActivity(intent);
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    private void inflateViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_workout_list_recyclerview);
        btNewWorkout = (FloatingActionButton) view.findViewById(R.id.fragment_workout_list__bt__new_workout);
        linlayEmptyList = (LinearLayout) view.findViewById(R.id.empty_list_workouts);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new WorkoutListAdapter(getPresenter().getWorkouts());
        recyclerView.setAdapter(adapter);
    }



    private void initFab() {
        btNewWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().onClickNewWorkout();
            }
        });
    }

    private void updateVisibilityOfViews() {
        boolean emptyList = getPresenter().getWorkouts().isEmpty();

        if (emptyList) {
            recyclerView.setVisibility(View.GONE);
            linlayEmptyList.setVisibility(View.VISIBLE);

        } else {
            linlayEmptyList.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

    }

    public WorkoutListPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(WorkoutListPresenter presenter) {
        this.presenter = presenter;
    }

}
