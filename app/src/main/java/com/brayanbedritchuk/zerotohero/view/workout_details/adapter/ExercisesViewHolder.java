package com.brayanbedritchuk.zerotohero.view.workout_details.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.model.Exercise;

public class ExercisesViewHolder extends RecyclerView.ViewHolder {

    private TextView tvName;
//    private TextView tvStyle;

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
//        tvStyle = (TextView) view.findViewById(R.id.view_holder_workout__tv__info);
    }

    public void bindData(Exercise exercise) {
        tvName.setText(exercise.getName());
//        tvStyle.setText(workout.getStyle());
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
