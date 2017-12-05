package br.com.sailboat.zerotohero.view.adapter.view_holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.sailboat.canoe.base.BaseViewHolder;
import br.com.sailboat.canoe.exception.EntityNotFoundException;
import br.com.sailboat.canoe.helper.DecimalHelper;
import br.com.sailboat.canoe.helper.LogHelper;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.model.sqlite.ExerciseHistory;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseHistorySQLite;

public class ExerciseViewHolder extends BaseViewHolder {

    private TextView tvName;
    private TextView tvWeight;
    private TextView tvSets;
    private TextView tvReps;

    private ExerciseViewHolder.Callback callback;


    public static ExerciseViewHolder newInstance(ViewGroup parent, ExerciseViewHolder.Callback callback) {
        View view = inflateLayout(parent, R.layout.vh_exercise);
        return new ExerciseViewHolder(view, callback);
    }


    public ExerciseViewHolder(View itemView, ExerciseViewHolder.Callback callback) {
        super(itemView);
        this.callback = callback;
        initViews(itemView);
        checkCallbackAndBindListeners(itemView);
    }

    public void onBindViewHolder(Exercise exercise) {
        bindTextViews(exercise);
    }

    private void bindTextViews(Exercise exercise) {

        try {
            tvName.setText(exercise.getName());
            ExerciseHistory lastHistory = ExerciseHistorySQLite.newInstance(itemView.getContext()).getMostRecentExerciseHistory(exercise.getId());
            tvWeight.setText(String.valueOf(DecimalHelper.formatValue(lastHistory.getWeight(), 1)));
            tvSets.setText(String.valueOf(lastHistory.getSets()) + " sets");
            tvReps.setText(String.valueOf(lastHistory.getReps()) + " reps");

        } catch (EntityNotFoundException e) {
            LogHelper.logException(e);
        }

    }

    private void initViews(View view) {
        tvName = (TextView) view.findViewById(R.id.exercise__tv__name);
        tvWeight = (TextView) view.findViewById(R.id.exercise__tv__weight);
        tvSets = (TextView) view.findViewById(R.id.exercise__tv__sets);
        tvReps = (TextView) view.findViewById(R.id.exercise__tv__reps);
    }

    private void checkCallbackAndBindListeners(View itemView) {
        if (callback != null) {
            bindListeners(itemView);
        }
    }

    private void bindListeners(View itemView) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onClickExercise(getAdapterPosition());
            }
        });
    }


    public interface Callback {
        void onClickExercise(int position);
    }
}
