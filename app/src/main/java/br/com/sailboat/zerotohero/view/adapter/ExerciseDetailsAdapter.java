package br.com.sailboat.zerotohero.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import br.com.sailboat.canoe.recycler.RecyclerItem;
import br.com.sailboat.zerotohero.helper.ViewType;
import br.com.sailboat.zerotohero.model.sqlite.ExerciseHistory;
import br.com.sailboat.zerotohero.model.view.ExerciseNameView;
import br.com.sailboat.zerotohero.model.view.LabelView;
import br.com.sailboat.zerotohero.model.view.LabelWithTextView;
import br.com.sailboat.zerotohero.model.view.PaddingView;
import br.com.sailboat.zerotohero.model.view.SubheaderView;
import br.com.sailboat.zerotohero.view.adapter.view_holder.ExerciseDetailsNameViewHolder;
import br.com.sailboat.zerotohero.view.adapter.view_holder.ExerciseHistoryViewHolder;
import br.com.sailboat.zerotohero.view.adapter.view_holder.LabelViewHolder;
import br.com.sailboat.zerotohero.view.adapter.view_holder.LabelWithTextViewHolder;
import br.com.sailboat.zerotohero.view.adapter.view_holder.PaddingViewHolder;
import br.com.sailboat.zerotohero.view.adapter.view_holder.SubheaderViewHolder;

public class ExerciseDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ExerciseDetailsAdapter.Callback callback;

    public ExerciseDetailsAdapter(Callback callback) {
        this.callback = callback;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case ViewType.EXERCISE_DETAIL_NAME: {
                return ExerciseDetailsNameViewHolder.newInstance(parent);
            }
            case ViewType.SUBHEADER: {
                return SubheaderViewHolder.newInstance(parent);
            }
            case ViewType.LABEL_TEXT: {
                return LabelWithTextViewHolder.newInstance(parent);
            }
            case ViewType.EXERCISE_HISTORY: {
                return ExerciseHistoryViewHolder.newInstance(parent);
            }
            case ViewType.PADDING: {
                return PaddingViewHolder.newInstance(parent);
            }
            case ViewType.LABEL: {
                return LabelViewHolder.newInstance(parent);
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
            case ViewType.EXERCISE_DETAIL_NAME: {
                ((ExerciseDetailsNameViewHolder) holder).onBindViewHolder((ExerciseNameView) item);
                return;
            }
            case ViewType.SUBHEADER: {
                ((SubheaderViewHolder) holder).onBindViewHolder((SubheaderView) item);
                return;
            }
            case ViewType.LABEL_TEXT: {
                ((LabelWithTextViewHolder) holder).onBindViewHolder((LabelWithTextView) item);
                return;
            }
            case ViewType.EXERCISE_HISTORY: {
                ((ExerciseHistoryViewHolder) holder).bindToView((ExerciseHistory) item);
                return;
            }
            case ViewType.PADDING: {
                ((PaddingViewHolder) holder).bindToView((PaddingView) item);
                return;
            }
            case ViewType.LABEL: {
                ((LabelViewHolder) holder).onBindViewHolder((LabelView) item);
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
