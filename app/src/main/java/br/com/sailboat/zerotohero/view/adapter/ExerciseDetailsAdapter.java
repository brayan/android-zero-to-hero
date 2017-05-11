package br.com.sailboat.zerotohero.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import br.com.sailboat.zerotohero.model.sqlite.ExerciseHistory;
import br.com.sailboat.zerotohero.view.adapter.view_holder.ExerciseHistoryViewHolder;

public class ExerciseDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ExerciseDetailsAdapter.Callback callback;

    public ExerciseDetailsAdapter(Callback callback) {
        this.callback = callback;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ExerciseHistoryViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExerciseHistory item = callback.getExerciseHistoryList().get(position);
        ((ExerciseHistoryViewHolder) holder).bindToView(item);
    }

    @Override
    public int getItemCount() {
        return callback.getExerciseHistoryList().size();
    }


    public interface Callback {
        List<ExerciseHistory> getExerciseHistoryList();
    }

}
