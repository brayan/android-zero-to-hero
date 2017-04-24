package br.com.sailboat.zerotohero.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.view.adapter.view_holder.ExerciseViewHolder;

public class ExercisesListAdapter extends RecyclerView.Adapter<ExerciseViewHolder> implements ItemTouchHelperAdapter {

    private List<Exercise> exerciseList;
    private ExercisesListAdapter.Callback callback;

    public ExercisesListAdapter(List<Exercise> items, ExercisesListAdapter.Callback callback) {
        setExerciseList(items);
        setCallback(callback);
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflateLayout(parent, ExerciseViewHolder.LAYOUT_ID);
        return new ExerciseViewHolder(view, getCallback());
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        Exercise exercise = getExerciseList().get(position);
        holder.onBindViewHolder(exercise);
    }

    @Override
    public int getItemCount() {
        return getExerciseList().size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(getExerciseList(), i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(getExerciseList(), i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        getExerciseList().remove(position);
        notifyItemRemoved(position);
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



    public interface Callback extends ExerciseViewHolder.Callback {

    }


}
