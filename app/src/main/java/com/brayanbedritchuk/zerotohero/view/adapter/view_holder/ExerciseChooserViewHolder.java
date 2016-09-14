package com.brayanbedritchuk.zerotohero.view.adapter.view_holder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.model.Exercise;

public class ExerciseChooserViewHolder extends RecyclerView.ViewHolder {

    public static final int LAYOUT_ID = R.layout.holder_exercise_chooser;

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
        initListeners(itemView);
    }

    private void initListeners(View itemView) {
        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCallback().onClickExercise(getAdapterPosition());
            }
        };

        itemView.setOnClickListener(onClick);
        cbSelected.setOnClickListener(onClick);
    }

    private void initViews(View view) {
        tvName = (TextView) view.findViewById(R.id.exercise__tv__name);
        tvWeight = (TextView) view.findViewById(R.id.exercise__tv__weight);
        tvSets = (TextView) view.findViewById(R.id.exercise__tv__sets);
        tvReps = (TextView) view.findViewById(R.id.exercise__tv__reps);
        cbSelected = (CheckBox) view.findViewById(R.id.holder_exercise_chooser__cbox);
    }

    public void onBindViewHolder(Exercise exercise, SparseArray<Exercise> selectedExercises) {
        tvName.setText(exercise.getName());
        tvWeight.setText(String.valueOf(exercise.getWeight()) + " KG ");
        tvSets.setText(String.valueOf(exercise.getSet()) + " sets ");
        tvReps.setText(String.valueOf(exercise.getRepetition()) + " reps");
        cbSelected.setChecked(selectedExercises.get(exercise.getId()) != null);
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
