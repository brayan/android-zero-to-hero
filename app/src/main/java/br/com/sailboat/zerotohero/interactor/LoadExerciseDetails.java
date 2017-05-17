package br.com.sailboat.zerotohero.interactor;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.helper.StringHelper;
import br.com.sailboat.canoe.recycler.RecyclerItem;
import br.com.sailboat.canoe.recycler.item.LabelRecyclerItem;
import br.com.sailboat.canoe.recycler.item.LabelValueRecyclerItem;
import br.com.sailboat.canoe.recycler.item.TitleRecyclerItem;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.ViewType;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.model.sqlite.ExerciseHistory;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseHistorySQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;

public class LoadExerciseDetails {

    public static List<RecyclerItem> loadExerciseDetails(Context context, long exerciseId) throws Exception {
        List<RecyclerItem> recyclerItems = new ArrayList<>();

        Exercise exercise = ExerciseSQLite.newInstance(context).getExerciseById(exerciseId);

        recyclerItems.add(getTitle(exercise));

        if (StringHelper.isNotEmpty(exercise.getNotes())) {
            recyclerItems.add(getLabelValueNotes(context, exercise));
        }

        List<ExerciseHistory> history = ExerciseHistorySQLite.newInstance(context).getMostRecentByExercise(exerciseId);

        if (!history.isEmpty()) {
            recyclerItems.add(getLabelHistory(context));
            recyclerItems.addAll(history);
        }


        return recyclerItems;
    }

    @NonNull
    private static LabelRecyclerItem getLabelHistory(Context context) {
        LabelRecyclerItem item = new LabelRecyclerItem(ViewType.LABEL);
        item.setLabel(context.getString(R.string.history));
        return item;
    }

    @NonNull
    private static LabelValueRecyclerItem getLabelValueNotes(Context context, Exercise exercise) {
        LabelValueRecyclerItem item = new LabelValueRecyclerItem(ViewType.LABEL_VALUE);
        item.setLabel(context.getString(R.string.notes));
        item.setValue(exercise.getNotes());
        return item;
    }

    @NonNull
    private static TitleRecyclerItem getTitle(Exercise exercise) {
        TitleRecyclerItem item = new TitleRecyclerItem(ViewType.TITLE);
        item.setTitle(exercise.getName());
        return item;
    }


}
