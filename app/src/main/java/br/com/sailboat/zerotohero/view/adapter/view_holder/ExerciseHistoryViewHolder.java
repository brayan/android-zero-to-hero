package br.com.sailboat.zerotohero.view.adapter.view_holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;

import br.com.sailboat.canoe.base.BaseViewHolder;
import br.com.sailboat.canoe.helper.DateHelper;
import br.com.sailboat.canoe.helper.DecimalHelper;
import br.com.sailboat.canoe.helper.LogHelper;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.model.sqlite.ExerciseHistory;

public class ExerciseHistoryViewHolder extends BaseViewHolder {

    private TextView tvWeight;
    private TextView tvReps;
    private TextView tvSets;
    private TextView tvDateTime;


    public static ExerciseHistoryViewHolder newInstance(ViewGroup parent) {
        View view = inflateLayout(parent, R.layout.exercise_history);
        return new ExerciseHistoryViewHolder(view);
    }


    public ExerciseHistoryViewHolder(View itemView) {
        super(itemView);
        initViews();
    }

    public void bindToView(ExerciseHistory item) {
        tvWeight.setText(DecimalHelper.formatValue(item.getWeight(), 1) + " KG");
        tvReps.setText(item.getReps() + " reps");
        tvSets.setText(item.getSets() + " sets");
        setDateTime(item);
    }

    private void setDateTime(ExerciseHistory item) {
        try {
            String date = DateHelper.parseStringWithDatabaseFormatToSimpleDate(itemView.getContext(), item.getLastModified());
            tvDateTime.setText(date);
        } catch (ParseException e) {
            LogHelper.logException(e);
            tvDateTime.setText("");
        }
    }

    private void initViews() {
        inflateViews();
    }

    private void inflateViews() {
        tvWeight = (TextView) itemView.findViewById(R.id.exercise_history__tv__weight);
        tvReps = (TextView) itemView.findViewById(R.id.exercise_history__tv__reps);
        tvSets = (TextView) itemView.findViewById(R.id.exercise_history__tv__sets);
        tvDateTime = (TextView) itemView.findViewById(R.id.exercise_history__tv__date);
    }


}
