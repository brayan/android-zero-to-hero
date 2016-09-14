package com.brayanbedritchuk.zerotohero.view.adapter.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.model.Exercise;

public class ExerciseViewHolder extends RecyclerView.ViewHolder {

    public static final int LAYOUT_ID = R.layout.holder_exercise;

    private TextView tvName;
    private TextView tvWeight;
    private TextView tvSets;
    private TextView tvReps;

    private ExerciseViewHolder.Callback callback;

    public ExerciseViewHolder(View itemView, ExerciseViewHolder.Callback callback) {
        super(itemView);
        setCallback(callback);
        initViews(itemView);
        verifyAndSetListeners(itemView);
    }

    public void onBindViewHolder(Exercise exercise) {
        tvName.setText(exercise.getName());
        tvWeight.setText(String.valueOf(exercise.getWeight()) + " KG ");
        tvSets.setText(String.valueOf(exercise.getSet()) + " sets ");
        tvReps.setText(String.valueOf(exercise.getRepetition()) + " reps");
    }

    private void initViews(View view) {
        tvName = (TextView) view.findViewById(R.id.exercise__tv__name);
        tvWeight = (TextView) view.findViewById(R.id.exercise__tv__weight);
        tvSets = (TextView) view.findViewById(R.id.exercise__tv__sets);
        tvReps = (TextView) view.findViewById(R.id.exercise__tv__reps);
    }

    private void verifyAndSetListeners(View itemView) {
        if (getCallback() != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getCallback().onClickWorkout(getAdapterPosition());
                }
            });
        }

    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {

        void onClickWorkout(int position);
    }
}
