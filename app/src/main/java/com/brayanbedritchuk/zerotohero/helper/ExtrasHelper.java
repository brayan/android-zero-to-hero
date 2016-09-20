package com.brayanbedritchuk.zerotohero.helper;

import android.content.Intent;

import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.model.Workout;

import java.util.ArrayList;
import java.util.List;

public class ExtrasHelper {

    private static final String WORKOUT = "WORKOUT";
    private static final String EXERCISE = "EXERCISE";
    private static final String ARRAY_LIST_EXERCISES = "ARRAY_LIST_EXERCISES";
    private static final String DELETE_WORKOUT = "DELETE_WORKOUT";
    private static final String DELETE_EXERCISE = "DELETE_EXERCISE";

    public static void putExercise(Exercise exercise, Intent intent) {
        intent.putExtra(EXERCISE, exercise);
    }

    public static void putWorkout(Workout workout, Intent intent) {
        intent.putExtra(WORKOUT, workout);
    }

    public static void putExercises(List<Exercise> exercises, Intent intent) {
        intent.putExtra(ARRAY_LIST_EXERCISES, (ArrayList) exercises);
    }

    public static Workout getWorkout(Intent intent) {
        return (Workout) intent.getSerializableExtra(WORKOUT);
    }

    public static List<Exercise> getExercises(Intent intent) {
        return (List) intent.getSerializableExtra(ARRAY_LIST_EXERCISES);
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

}
