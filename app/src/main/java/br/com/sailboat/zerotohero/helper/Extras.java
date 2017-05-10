package br.com.sailboat.zerotohero.helper;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.model.view.ExerciseView;

public class Extras {

    private static final String WORKOUT_ID = "WORKOUT_ID";
    private static final String EXERCISE_ID = "EXERCISE_ID";
    private static final String LIST_EXERCISE = "LIST_EXERCISE";
    private static final String LIST_EXERCISE_VIEW = "LIST_EXERCISE_VIEW";
    private static final String DELETE_EXERCISE = "DELETE_EXERCISE";

    public static void putExerciseId(long exerciseId, Intent intent) {
        intent.putExtra(EXERCISE_ID, exerciseId);
    }

    public static void putWorkoutId(long workoutId, Intent intent) {
        intent.putExtra(WORKOUT_ID, workoutId);
    }

    public static void putExercises(List<Exercise> exercises, Intent intent) {
        intent.putExtra(LIST_EXERCISE, (ArrayList) exercises);
    }

    public static void putExerciseViewList(List<ExerciseView> exercises, Intent intent) {
        intent.putExtra(LIST_EXERCISE_VIEW, (ArrayList) exercises);
    }

    public static void putExerciseViewList(List<ExerciseView> exercises, Bundle bundle) {
        bundle.putSerializable(LIST_EXERCISE_VIEW, (ArrayList) exercises);
    }

    public static long getWorkoutId(Intent intent) {
        return intent.getLongExtra(WORKOUT_ID, -1);
    }

    public static List<Exercise> getExercises(Intent intent) {
        return (List) intent.getSerializableExtra(LIST_EXERCISE);
    }

    public static List<ExerciseView> getExerciseViewList(Intent intent) {
        return (List) intent.getSerializableExtra(LIST_EXERCISE_VIEW);
    }

    public static List<ExerciseView> getExerciseViewList(Bundle bundle) {
        return (List) bundle.getSerializable(LIST_EXERCISE_VIEW);
    }

    public static long getExerciseId(Intent intent) {
        return intent.getLongExtra(EXERCISE_ID, -1);
    }

    public static boolean hasExerciseToDelete(Intent intent) {
        return intent.hasExtra(DELETE_EXERCISE);
    }

    public static void deleteExercise(Intent intent) {
        intent.putExtra(DELETE_EXERCISE, true);
    }
}
