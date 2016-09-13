package com.brayanbedritchuk.zerotohero.view.workout_details.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.model.Exercise;

public class ExercisesViewHolder extends RecyclerView.ViewHolder {

    private TextView tvName;
    private TextView tvWeight;
    private TextView tvSets;
    private TextView tvReps;

    private ExercisesViewHolder.Callback callback;


    public interface Callback {

        void onClickWorkout(int position);
    }

    public ExercisesViewHolder(View itemView, ExercisesViewHolder.Callback callback) {
        super(itemView);
        setCallback(callback);
        initViews(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCallback().onClickWorkout(getAdapterPosition());
            }
        });
    }

    private void initViews(View view) {
        tvName = (TextView) view.findViewById(R.id.holder_exercise__tv__name);
        tvWeight = (TextView) view.findViewById(R.id.holder_exercise__tv__weight);
        tvSets = (TextView) view.findViewById(R.id.holder_exercise__tv__sets);
        tvReps = (TextView) view.findViewById(R.id.holder_exercise__tv__reps);
    }

    public void bindData(Exercise exercise) {
        tvName.setText(exercise.getName());
        tvWeight.setText(String.valueOf(exercise.getWeight()) + " KG ");
        tvSets.setText(String.valueOf(exercise.getSet()) + " sets ");
        tvReps.setText(String.valueOf(exercise.getRepetition()) + " reps");
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
