package com.brayanbedritchuk.zerotohero.view.insert_or_edit_workout.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.model.Workout;

public class ExercisesViewHolder extends RecyclerView.ViewHolder {

    private TextView tvName;
//    private TextView tvStyle;


    public interface Callback {

    }

    public ExercisesViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
}
