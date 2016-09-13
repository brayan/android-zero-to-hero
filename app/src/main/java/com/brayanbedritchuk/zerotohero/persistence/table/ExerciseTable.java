package com.brayanbedritchuk.zerotohero.persistence.table;


import android.database.Cursor;

import com.brayanbedritchuk.zerotohero.base.BaseSQLiteTable;
import com.brayanbedritchuk.zerotohero.model.Exercise;

public class ExerciseTable extends BaseSQLiteTable {

    @Override
    public String getSqlCreateTable() {
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE Exercise ( ");
        sb.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" name TEXT, ");
        sb.append(" weight REAL, ");
        sb.append(" exerciseSet INTEGER, ");
        sb.append(" repetition INTEGER ");
        sb.append(" ); ");
        return  sb.toString();
    }

    public static String createQueryGetAll() {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT Exercise.* FROM Exercise ");

        return query.toString();
    }

    public static String createQueryGetFromWorkout(long workoutId) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT Exercise.* FROM Exercise ");
        query.append(" INNER JOIN WorkoutExercise ");
        query.append(" ON Exercise.id = WorkoutExercise.exerciseId ");
        query.append(" WHERE WorkoutExercise.workoutId = " + workoutId);

        return query.toString();
    }

    public static Exercise getExerciseFromCursor(Cursor cursor) {

        Exercise exercise = new Exercise();
        exercise.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
        exercise.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
        exercise.setRepetition(cursor.getInt(cursor.getColumnIndexOrThrow("repetition")));
        exercise.setSet(cursor.getInt(cursor.getColumnIndexOrThrow("exerciseSet")));
        exercise.setWeight(cursor.getDouble(cursor.getColumnIndexOrThrow("weight")));

        return exercise;
    }
}
