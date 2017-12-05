package br.com.sailboat.zerotohero.helper;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.helper.EntityHelper;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;

public class ExtrasHelper {

    private static final String STARTED_FROM_MENU = "STARTED_FROM_MENU";
    private static final String WORKOUT_ID = "WORKOUT_ID";
    private static final String EXERCISE_ID = "EXERCISE_ID";
    private static final String EXERCISE_VIEW = "EXERCISE_VIEW";
    private static final String LIST_EXERCISE = "LIST_EXERCISE";
    private static final String LIST_EXERCISE_VIEW = "LIST_EXERCISE_VIEW";
    private static final String DELETE_EXERCISE = "DELETE_EXERCISE";

    public static void putStartedFromMenu(boolean started, Intent intent) {
        intent.putExtra(STARTED_FROM_MENU, started);
    }

    public static boolean wasStartedFromMenu(Intent intent) {
        return intent != null && intent.getBooleanExtra(STARTED_FROM_MENU, false);
    }

    public static void putExerciseId(long exerciseId, Bundle bundle) {
        bundle.putLong(EXERCISE_ID, exerciseId);
    }

    public static void putExerciseId(long exerciseId, Intent intent) {
        intent.putExtra(EXERCISE_ID, exerciseId);
    }

    public static void putWorkoutId(long workoutId, Intent intent) {
        intent.putExtra(WORKOUT_ID, workoutId);
    }

    public static void putWorkoutId(long workoutId, Bundle bundle) {
        bundle.putLong(WORKOUT_ID, workoutId);
    }

    public static void putExercises(List<Exercise> exercises, Intent intent) {
        intent.putExtra(LIST_EXERCISE, (ArrayList) exercises);
    }

    public static void putExercises(List<Exercise> exercises, Bundle bundle) {
        bundle.putSerializable(LIST_EXERCISE, (ArrayList) exercises);
    }

    public static long getWorkoutId(Intent intent) {
        return intent.getLongExtra(WORKOUT_ID, EntityHelper.NO_ID);
    }

    public static long getWorkoutId(Bundle bundle) {
        return bundle.getLong(WORKOUT_ID, EntityHelper.NO_ID);
    }

    public static List<Exercise> getExercises(Intent intent) {
        return (List) intent.getSerializableExtra(LIST_EXERCISE);
    }

    public static List<Exercise> getExercises(Bundle bundle) {
        return (List) bundle.getSerializable(LIST_EXERCISE);
    }

    public static long getExerciseId(Intent intent) {
        return intent.getLongExtra(EXERCISE_ID, EntityHelper.NO_ID);
    }

    public static long getExerciseId(Bundle bundle) {
        return bundle.getLong(EXERCISE_ID, EntityHelper.NO_ID);
    }

    public static boolean hasExerciseToDelete(Intent intent) {
        return intent.hasExtra(DELETE_EXERCISE);
    }

    public static void deleteExercise(Intent intent) {
        intent.putExtra(DELETE_EXERCISE, true);
    }


}
