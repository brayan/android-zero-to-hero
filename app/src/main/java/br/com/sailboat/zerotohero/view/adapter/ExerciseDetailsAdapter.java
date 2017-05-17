package br.com.sailboat.zerotohero.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import br.com.sailboat.canoe.recycler.RecyclerItem;
import br.com.sailboat.canoe.recycler.item.LabelRecyclerItem;
import br.com.sailboat.canoe.recycler.item.LabelValueRecyclerItem;
import br.com.sailboat.canoe.recycler.item.TitleRecyclerItem;
import br.com.sailboat.canoe.recycler.view_holder.LabelValueViewHolder;
import br.com.sailboat.canoe.recycler.view_holder.LabelViewHolder;
import br.com.sailboat.canoe.recycler.view_holder.TitleViewHolder;
import br.com.sailboat.zerotohero.helper.ViewType;
import br.com.sailboat.zerotohero.model.sqlite.ExerciseHistory;
import br.com.sailboat.zerotohero.view.adapter.view_holder.ExerciseHistoryViewHolder;

public class ExerciseDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ExerciseDetailsAdapter.Callback callback;

    public ExerciseDetailsAdapter(Callback callback) {
        this.callback = callback;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case ViewType.TITLE: {
                return TitleViewHolder.newInstance(parent);
            }
            case ViewType.EXERCISE_HISTORY: {
                return ExerciseHistoryViewHolder.newInstance(parent);
            }
            case ViewType.LABEL: {
                return LabelViewHolder.newInstance(parent);
            }
            case ViewType.LABEL_VALUE: {
                return LabelValueViewHolder.newInstance(parent);
            }
            default: {
                throw new RuntimeException("ViewHolder not found");
            }
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerItem item = callback.getExerciseDetails().get(position);

        switch (item.getViewType()) {
            case  ViewType.TITLE: {
                ((TitleViewHolder) holder).bindItem((TitleRecyclerItem) item);
                return;
            }
            case ViewType.EXERCISE_HISTORY: {
                ((ExerciseHistoryViewHolder) holder).bindItem((ExerciseHistory) item);
                return;
            }
            case ViewType.LABEL: {
                ((LabelViewHolder) holder).bindItem(((LabelRecyclerItem) item));
                return;
            }
            case ViewType.LABEL_VALUE: {
                ((LabelValueViewHolder) holder).bindItem((LabelValueRecyclerItem) item);
                return;
            }
            default: {
                throw new RuntimeException("ViewHolder not found");
            }
        }

    }

    @Override
    public int getItemCount() {
        return callback.getExerciseDetails().size();
    }

    @Override
    public int getItemViewType(int position) {
        return callback.getExerciseDetails().get(position).getViewType();
    }

    public interface Callback {

        List<RecyclerItem> getExerciseDetails();
    }

}
