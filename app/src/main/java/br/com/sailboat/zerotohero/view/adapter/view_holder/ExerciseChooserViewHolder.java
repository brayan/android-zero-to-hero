package br.com.sailboat.zerotohero.view.adapter.view_holder;

import android.support.v7.widget.RecyclerView;
import android.util.LongSparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.model.view.ExerciseView;

public class ExerciseChooserViewHolder extends RecyclerView.ViewHolder {

    public static final int LAYOUT_ID = R.layout.vh_exercise_selector;

    private TextView tvName;
    private TextView tvWeight;
    private TextView tvSets;
    private TextView tvReps;
    private CheckBox cbSelected;

    private ExerciseChooserViewHolder.Callback callback;

    public ExerciseChooserViewHolder(View itemView, ExerciseChooserViewHolder.Callback callback) {
        super(itemView);
        setCallback(callback);
        initViews(itemView);
        checkCallbackAndBindListeners(itemView);
    }

    public void onBindViewHolder(ExerciseView exercise, LongSparseArray<ExerciseView> selectedExercises) {
        bindTextViews(exercise);
        bindCheckboxSelected(exercise, selectedExercises);
    }

    private void bindTextViews(ExerciseView exercise) {
        tvName.setText(exercise.getName());
        tvWeight.setText(String.valueOf(exercise.getWeight()) + " KG ");
        tvSets.setText(String.valueOf(exercise.getSet()) + " sets ");
        tvReps.setText(String.valueOf(exercise.getRep()) + " reps");
    }

    private void bindCheckboxSelected(ExerciseView exercise, LongSparseArray<ExerciseView> selectedExercises) {
        ExerciseView selectedExercise = selectedExercises.get(exercise.getExerciseId());
        boolean isSelected = (selectedExercise != null);
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
    }

}
