package br.com.sailboat.zerotohero.view.adapter.view_holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import br.com.sailboat.canoe.base.BaseViewHolder;
import br.com.sailboat.canoe.exception.EntityNotFoundException;
import br.com.sailboat.canoe.helper.DecimalHelper;
import br.com.sailboat.canoe.helper.LogHelper;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.model.sqlite.ExerciseHistory;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseHistorySQLite;

public class ExerciseSelectorViewHolder extends BaseViewHolder {

    private TextView tvName;
    private TextView tvWeight;
    private TextView tvSets;
    private TextView tvReps;
    private CheckBox cbSelected;

    private ExerciseSelectorViewHolder.Callback callback;


    public static ExerciseSelectorViewHolder newInstance(ViewGroup parent, ExerciseSelectorViewHolder.Callback callback) {
        View view = inflateLayout(parent, R.layout.vh_exercise_selector);
        return new ExerciseSelectorViewHolder(view, callback);
    }


    public ExerciseSelectorViewHolder(View itemView, ExerciseSelectorViewHolder.Callback callback) {
        super(itemView);
        setCallback(callback);
        initViews(itemView);
        checkCallbackAndBindListeners(itemView);
    }

    public void onBindViewHolder(Exercise exercise) {
        bindTextViews(exercise);
        bindCheckboxSelected(exercise);
    }

    private void bindTextViews(Exercise exercise) {
        try {
            tvName.setText(exercise.getName());
            ExerciseHistory lastHistory = ExerciseHistorySQLite.newInstance(itemView.getContext()).getMostRecentExerciseHistory(exercise.getId());
            tvWeight.setText(DecimalHelper.formatValue(lastHistory.getWeight(), 1));
            tvSets.setText(String.valueOf(lastHistory.getSets()) + " sets ");
            tvReps.setText(String.valueOf(lastHistory.getReps()) + " reps");
        } catch (EntityNotFoundException e) {
            LogHelper.logException(e);
        }
    }

    private void bindCheckboxSelected(Exercise exercise) {
        boolean isSelected = callback.isExerciseSelected(exercise);
        cbSelected.setChecked(isSelected);
    }

    private void initViews(View view) {
        tvName = (TextView) view.findViewById(R.id.exercise__tv__name);
        tvWeight = (TextView) view.findViewById(R.id.exercise__tv__weight);
        tvSets = (TextView) view.findViewById(R.id.exercise__tv__sets);
        tvReps = (TextView) view.findViewById(R.id.exercise__tv__reps);
        cbSelected = (CheckBox) view.findViewById(R.id.vh_exercise_selector__cb);
    }

    private void checkCallbackAndBindListeners(View itemView) {
        if (getCallback() != null) {
            bindListeners(itemView);
        }
    }

    private void bindListeners(View itemView) {
        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCallback().onClickExercise(getAdapterPosition());
            }
        };

        itemView.setOnClickListener(onClick);
        cbSelected.setOnClickListener(onClick);
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    public interface Callback {

        void onClickExercise(int position);

        boolean isExerciseSelected(Exercise exercise);
    }

}
