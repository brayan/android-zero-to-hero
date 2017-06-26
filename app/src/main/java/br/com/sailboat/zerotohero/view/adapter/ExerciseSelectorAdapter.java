package br.com.sailboat.zerotohero.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.view.adapter.view_holder.ExerciseSelectorViewHolder;

public class ExerciseSelectorAdapter extends RecyclerView.Adapter<ExerciseSelectorViewHolder> {

    private ExerciseSelectorAdapter.Callback callback;

    public ExerciseSelectorAdapter(ExerciseSelectorAdapter.Callback callback) {
        this.callback = callback;
    }

    @Override
    public ExerciseSelectorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ExerciseSelectorViewHolder.newInstance(parent, callback);
    }

    @Override
    public void onBindViewHolder(ExerciseSelectorViewHolder holder, int position) {
        Exercise exercise = callback.getExerciseList().get(position);
        holder.onBindViewHolder(exercise);
    }

    @Override
    public int getItemCount() {
        return callback.getExerciseList().size();
    }


    public interface Callback extends ExerciseSelectorViewHolder.Callback {
        List<Exercise> getExerciseList();
    }


}
