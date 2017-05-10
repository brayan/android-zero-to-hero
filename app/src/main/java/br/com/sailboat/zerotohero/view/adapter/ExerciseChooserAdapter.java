package br.com.sailboat.zerotohero.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.sailboat.zerotohero.model.view.ExerciseView;
import br.com.sailboat.zerotohero.view.adapter.view_holder.ExerciseChooserViewHolder;

public class ExerciseChooserAdapter extends RecyclerView.Adapter<ExerciseChooserViewHolder> {

    private ExerciseChooserAdapter.Callback callback;

    public ExerciseChooserAdapter(ExerciseChooserAdapter.Callback callback) {
        this.callback = callback;
    }

    @Override
    public ExerciseChooserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflateLayout(parent, ExerciseChooserViewHolder.LAYOUT_ID);
        return new ExerciseChooserViewHolder(view, callback);
    }

    @Override
    public void onBindViewHolder(ExerciseChooserViewHolder holder, int position) {
        ExerciseView exercise = callback.getExerciseList().get(position);
        holder.onBindViewHolder(exercise, callback.getSelectedExercises());
    }

    @Override
    public int getItemCount() {
        return callback.getExerciseList().size();
    }

    private View inflateLayout(ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }


    public interface Callback extends ExerciseChooserViewHolder.Callback {
        LongSparseArray<ExerciseView> getSelectedExercises();
        List<ExerciseView> getExerciseList();
    }
}
