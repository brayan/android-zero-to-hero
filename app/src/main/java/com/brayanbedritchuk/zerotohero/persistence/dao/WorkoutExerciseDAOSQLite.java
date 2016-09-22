package com.brayanbedritchuk.zerotohero.persistence.dao;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;

import com.brayanbedritchuk.zerotohero.base.BaseDAOSQLite;

public class WorkoutExerciseDAOSQLite extends BaseDAOSQLite {

    public WorkoutExerciseDAOSQLite(Context context) {
        super(context);
    }

    public long save(long workoutId, long exerciseId) throws Exception {
        getWritableDatabase().beginTransactionNonExclusive();
        try {
            StringBuilder sql = new StringBuilder();

            sql.append(" INSERT INTO WorkoutExercise ");
            sql.append(" (workoutId, exerciseId) ");
            sql.append(" VALUES (?, ?); ");

            SQLiteStatement stmt = getWritableDatabase().compileStatement(sql.toString());
            stmt.bindLong(1, workoutId);
            stmt.bindLong(2, exerciseId);

            long id = stmt.executeInsert();

            stmt.clearBindings();

            getWritableDatabase().setTransactionSuccessful();

            return id;
        } finally {
            getWritableDatabase().endTransaction();
        }
    }

    public void deleteFromWorkout(long workoutId) throws Exception {
        getWritableDatabase().beginTransactionNonExclusive();
        try {
            String sql = "DELETE FROM WorkoutExercise WHERE WorkoutExercise.workoutId = ?";
            SQLiteStatement stmt = getWritableDatabase().compileStatement(sql);
            stmt.bindLong(1, workoutId);
            stmt.executeUpdateDelete();
            stmt.clearBindings();

            getWritableDatabase().setTransactionSuccessful();
        } finally {
            getWritableDatabase().endTransaction();
        }
    }

    public void deleteFromExercise(long exerciseId) throws Exception {
        getWritableDatabase().beginTransactionNonExclusive();
        try {
            String sql = "DELETE FROM WorkoutExercise WHERE WorkoutExercise.exerciseId = ?";
            SQLiteStatement stmt = getWritableDatabase().compileStatement(sql);
            stmt.bindLong(1, exerciseId);
            stmt.executeUpdateDelete();
            stmt.clearBindings();

            getWritableDatabase().setTransactionSuccessful();
        } finally {
            getWritableDatabase().endTransaction();
        }
    }
}
