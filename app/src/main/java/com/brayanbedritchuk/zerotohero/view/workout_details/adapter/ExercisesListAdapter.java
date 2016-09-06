package com.brayanbedritchuk.zerotohero.view.workout_details.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.model.entity.Exercise;

import java.util.List;

public class ExercisesListAdapter extends RecyclerView.Adapter<ExercisesViewHolder> {

    private List<Exercise> exerciseList;
    private ExercisesListAdapter.Callback callback;

    public ExercisesListAdapter(List<Exercise> items, ExercisesListAdapter.Callback callback) {
        setExerciseList(items);
        setCallback(callback);
    }
    @Override
    public ExercisesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflateLayout(parent, R.layout.holder_exercise);
        return new ExercisesViewHolder(view, getCallback());
    }

    @Override
    public void onBindViewHolder(ExercisesViewHolder holder, int position) {
        Exercise exercise = getExerciseList().get(position);
        holder.bindData(exercise);
    }

    @Override
    public int getItemCount() {
        return getExerciseList().size();
    }

    private View inflateLayout(ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback extends ExercisesViewHolder.Callback {
    }
}
