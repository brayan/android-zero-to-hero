package com.brayanbedritchuk.zerotohero.ui.workout_list.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.model.Workout;

import java.util.List;

public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutViewHolder> {

    private List<Workout> workoutList;

    public WorkoutListAdapter(List<Workout> items) {
        setWorkoutList(items);
    }
    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflateLayout(parent, R.layout.view_holder_workout);
        return new WorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WorkoutViewHolder holder, int position) {
        Workout beer = getWorkoutList().get(position);
        holder.bindData(beer);
    }

    @Override
    public int getItemCount() {
        return getWorkoutList().size();
    }

    private View inflateLayout(ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    public List<Workout> getWorkoutList() {
        return workoutList;
    }

    public void setWorkoutList(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

}
