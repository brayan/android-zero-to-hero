package br.com.sailboat.zerotohero.interactor;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.recycler.RecyclerItem;
import br.com.sailboat.canoe.recycler.item.LabelRecyclerItem;
import br.com.sailboat.canoe.recycler.item.TitleRecyclerItem;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.ViewType;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.model.sqlite.Workout;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutSQLite;

public class LoadWorkoutDetails {

    public static List<RecyclerItem> loadWorkoutDetails(Context context, long workoutId) throws Exception {
        List<RecyclerItem> recyclerItems = new ArrayList<>();

        Workout workout = WorkoutSQLite.newInstance(context).getWorkoutById(workoutId);

        recyclerItems.add(getTitle(workout));

        List<Exercise> exercises = ExerciseSQLite.newInstance(context).getFromWorkout(workoutId);

        if (!exercises.isEmpty()) {
            recyclerItems.add(getLabelExercises(context));
            recyclerItems.addAll(exercises);
        }

        return recyclerItems;
    }

    @NonNull
    private static LabelRecyclerItem getLabelExercises(Context context) {
        LabelRecyclerItem item = new LabelRecyclerItem(ViewType.LABEL);
        item.setLabel(context.getString(R.string.exercises));
        return item;
    }

    @NonNull
    private static TitleRecyclerItem getTitle(Workout workout) {
        TitleRecyclerItem item = new TitleRecyclerItem(ViewType.TITLE);
        item.setTitle(workout.getName());
        return item;
    }

}
