package com.brayanbedritchuk.zerotohero.view.workout_list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.model.Workout;

public class WorkoutViewHolder extends RecyclerView.ViewHolder {

    private TextView tvName;
//    private TextView tvStyle;

    private WorkoutViewHolder.Callback callback;


    public interface Callback {

        void onClickWorkout(int position);
    }

    public WorkoutViewHolder(View itemView, WorkoutViewHolder.Callback callback) {
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
        tvName = (TextView) view.findViewById(R.id.view_holder_workout__tv__name);
//        tvStyle = (TextView) view.findViewById(R.id.view_holder_workout__tv__info);
    }

    public void bindData(Workout workout) {
        tvName.setText(workout.getName());
//        tvStyle.setText(workout.getStyle());
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
