package com.brayanbedritchuk.zerotohero.view.new_workout.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.model.entity.Workout;

import java.util.List;

public class NewWorkoutExercicesAdapter extends RecyclerView.Adapter<ExercisesViewHolder> {

    private List<Workout> workoutList;

    public NewWorkoutExercicesAdapter(List<Workout> items) {
        setWorkoutList(items);
    }
    @Override
    public ExercisesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflateLayout(parent, R.layout.holder_workout);
        return new ExercisesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExercisesViewHolder holder, int position) {
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
