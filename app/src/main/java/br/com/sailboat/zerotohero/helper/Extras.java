package br.com.sailboat.zerotohero.helper;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.model.Workout;

public class Extras {

    private static final String WORKOUT = "WORKOUT";
    private static final String EXERCISE = "EXERCISE";
    private static final String LIST_EXERCISES = "LIST_EXERCISES";
    private static final String DELETE_WORKOUT = "DELETE_WORKOUT";
    private static final String DELETE_EXERCISE = "DELETE_EXERCISE";

    public static void putExercise(Exercise exercise, Intent intent) {
        intent.putExtra(EXERCISE, exercise);
    }

    public static void putWorkout(Workout workout, Intent intent) {
        intent.putExtra(WORKOUT, workout);
    }

    public static void putExercises(List<Exercise> exercises, Intent intent) {
        intent.putExtra(LIST_EXERCISES, (ArrayList) exercises);
    }

    public static Workout getWorkout(Intent intent) {
        return (Workout) intent.getSerializableExtra(WORKOUT);
    }

    public static List<Exercise> getExercises(Intent intent) {
        return (List) intent.getSerializableExtra(LIST_EXERCISES);
    }

    public static Exercise getExercise(Intent intent) {
        return (Exercise) intent.getSerializableExtra(EXERCISE);
    }

    public static void deleteWorkout(Intent intent) {
        intent.putExtra(DELETE_WORKOUT, true);
    }

    public static boolean hasWorkoutToDelete(Intent intent) {
        return intent.hasExtra(DELETE_WORKOUT);
    }

    public static boolean hasExerciseToDelete(Intent intent) {
        return intent.hasExtra(DELETE_EXERCISE);
    }

    public static void deleteExercise(Intent intent) {
        intent.putExtra(DELETE_EXERCISE, true);
    }
}
