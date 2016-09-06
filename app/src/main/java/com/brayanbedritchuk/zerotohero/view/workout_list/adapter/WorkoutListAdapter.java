package com.brayanbedritchuk.zerotohero.view.workout_list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.model.entity.Workout;

import java.util.List;

public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutViewHolder> {

    private List<Workout> workoutList;
    private WorkoutListAdapter.Callback callback;

    public WorkoutListAdapter(List<Workout> items, WorkoutListAdapter.Callback callback) {
        setWorkoutList(items);
        setCallback(callback);
    }
    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflateLayout(parent, R.layout.holder_workout);
        return new WorkoutViewHolder(view, getCallback());
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

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback extends WorkoutViewHolder.Callback {
    }
}
