package br.com.sailboat.zerotohero.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.view.adapter.view_holder.ExerciseChooserViewHolder;

public class ExerciseChooserAdapter extends RecyclerView.Adapter<ExerciseChooserViewHolder> {

    private LongSparseArray<Exercise> selectedExercises;
    private List<Exercise> exerciseList;
    private ExerciseChooserAdapter.Callback callback;

    public ExerciseChooserAdapter(List<Exercise> items, LongSparseArray<Exercise> selectedExercises, ExerciseChooserAdapter.Callback callback) {
        setExerciseList(items);
        setCallback(callback);
        setSelectedExercises(selectedExercises);
    }

    @Override
    public ExerciseChooserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflateLayout(parent, ExerciseChooserViewHolder.LAYOUT_ID);
        return new ExerciseChooserViewHolder(view, getCallback());
    }

    @Override
    public void onBindViewHolder(ExerciseChooserViewHolder holder, int position) {
        Exercise exercise = getExerciseList().get(position);
        holder.onBindViewHolder(exercise, getSelectedExercises());
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

    public LongSparseArray<Exercise> getSelectedExercises() {
        return selectedExercises;
    }

    public void setSelectedExercises(LongSparseArray<Exercise> selectedExercises) {
        this.selectedExercises = selectedExercises;
    }



    public interface Callback extends ExerciseChooserViewHolder.Callback {

    }
}
