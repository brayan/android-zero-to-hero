package br.com.sailboat.zerotohero.persistence.dao;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;

import br.com.sailboat.zerotohero.base.BaseDAOSQLite;

public class WorkoutExerciseDAOSQLite extends BaseDAOSQLite {

    public WorkoutExerciseDAOSQLite(Context context) {
        super(context);
    }

    public long saveAndGetId(long workoutId, long exerciseId) throws Exception {
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
        String query = "DELETE FROM WorkoutExercise WHERE WorkoutExercise.workoutId = ?";
        deleteFromID(query, workoutId);
    }

    public void deleteFromExercise(long exerciseId) throws Exception {
        String query = "DELETE FROM WorkoutExercise WHERE WorkoutExercise.exerciseId = ?";
        deleteFromID(query, exerciseId);
    }

    private void deleteFromID(String query, long id) {
        getWritableDatabase().beginTransactionNonExclusive();
        try {
            SQLiteStatement stmt = getWritableDatabase().compileStatement(query);
            stmt.bindLong(1, id);
            stmt.executeUpdateDelete();
            stmt.clearBindings();

            getWritableDatabase().setTransactionSuccessful();
        } finally {
            getWritableDatabase().endTransaction();
        }
    }
}
