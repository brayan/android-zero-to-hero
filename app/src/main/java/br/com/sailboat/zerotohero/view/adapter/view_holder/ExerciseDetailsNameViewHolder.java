package br.com.sailboat.zerotohero.view.adapter.view_holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.sailboat.canoe.base.BaseViewHolder;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.model.view.ExerciseNameView;

public class ExerciseDetailsNameViewHolder extends BaseViewHolder {

    private TextView tvName;


    public static ExerciseDetailsNameViewHolder newInstance(ViewGroup parent) {
        View view = inflateLayout(parent, R.layout.vh_exercise_name);
        return new ExerciseDetailsNameViewHolder(view);
    }


    public ExerciseDetailsNameViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
    }

    public void onBindViewHolder(ExerciseNameView exerciseName) {
        tvName.setText(exerciseName.getName());
    }

    private void initViews(View view) {
        tvName = (TextView) view.findViewById(R.id.vh_exercise_name__tv__name);
    }

}
