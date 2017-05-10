package br.com.sailboat.zerotohero.view.adapter.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.model.view.ExerciseView;

public class ExerciseViewHolder extends RecyclerView.ViewHolder {

    public static final int LAYOUT_ID = R.layout.vh_exercise;

    private TextView tvName;
    private TextView tvWeight;
    private TextView tvSets;
    private TextView tvReps;

    private ExerciseViewHolder.Callback callback;

    public ExerciseViewHolder(View itemView, ExerciseViewHolder.Callback callback) {
        super(itemView);
        this.callback = callback;
        initViews(itemView);
        checkCallbackAndBindListeners(itemView);
    }

    public void onBindViewHolder(ExerciseView exercise) {
        bindTextViews(exercise);
    }

    private void bindTextViews(ExerciseView exercise) {
        tvName.setText(exercise.getName());
        tvWeight.setText(String.valueOf(exercise.getWeight()) + " KG ");
        tvSets.setText(String.valueOf(exercise.getSet()) + " sets ");
        tvReps.setText(String.valueOf(exercise.getRep()) + " reps");
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
