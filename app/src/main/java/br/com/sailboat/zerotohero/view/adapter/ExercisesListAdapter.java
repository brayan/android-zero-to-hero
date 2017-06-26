package br.com.sailboat.zerotohero.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.view.adapter.view_holder.ExerciseViewHolder;

public class ExercisesListAdapter extends RecyclerView.Adapter<ExerciseViewHolder> implements ItemTouchHelperAdapter {

    private ExercisesListAdapter.Callback callback;

    public ExercisesListAdapter(ExercisesListAdapter.Callback callback) {
        this.callback = callback;
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ExerciseViewHolder.newInstance(parent, callback);
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        Exercise exercise = callback.getExercises().get(position);
        holder.onBindViewHolder(exercise);
    }

    @Override
    public int getItemCount() {
        return callback.getExercises().size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(callback.getExercises(), i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(callback.getExercises(), i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        callback.getExercises().remove(position);
        notifyItemRemoved(position);
    }

    private View inflateLayout(ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }


    public interface Callback extends ExerciseViewHolder.Callback {
        List<Exercise> getExercises();
    }


}
