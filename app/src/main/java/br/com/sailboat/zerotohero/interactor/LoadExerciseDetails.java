package br.com.sailboat.zerotohero.interactor;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.helper.StringHelper;
import br.com.sailboat.canoe.recycler.RecyclerItem;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.model.sqlite.ExerciseHistory;
import br.com.sailboat.zerotohero.model.view.ExerciseNameView;
import br.com.sailboat.zerotohero.model.view.LabelView;
import br.com.sailboat.zerotohero.model.view.LabelWithTextView;
import br.com.sailboat.zerotohero.model.view.PaddingView;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseHistorySQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;

public class LoadExerciseDetails {

    public static List<RecyclerItem> loadExerciseDetails(Context context, long exerciseId) throws Exception {
        List<RecyclerItem> details = new ArrayList<>();

        Exercise exercise = ExerciseSQLite.newInstance(context).getExerciseById(exerciseId);

        details.add(new ExerciseNameView(exercise.getName()));

        if (StringHelper.isNotEmpty(exercise.getNotes())) {
            details.add(new PaddingView(16));
            details.add(new LabelWithTextView(context.getString(R.string.notes), exercise.getNotes()));
        }

        List<ExerciseHistory> history = ExerciseHistorySQLite.newInstance(context).getMostRecentByExercise(exerciseId);

        if (!history.isEmpty()) {
            details.add(new PaddingView(28));
            details.add(new LabelView(context.getString(R.string.history)));
            details.addAll(history);
        }


        return details;
    }


}
