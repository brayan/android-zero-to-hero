package br.com.sailboat.zerotohero.view.adapter;

import android.support.v7.widget.RecyclerView;
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
        return ExerciseChooserViewHolder.newInstance(parent, callback);
    }

    @Override
    public void onBindViewHolder(ExerciseChooserViewHolder holder, int position) {
        ExerciseView exercise = callback.getExerciseList().get(position);
        holder.onBindViewHolder(exercise);
    }

    @Override
    public int getItemCount() {
        return callback.getExerciseList().size();
    }


    public interface Callback extends ExerciseChooserViewHolder.Callback {
        List<ExerciseView> getExerciseList();
    }


}
