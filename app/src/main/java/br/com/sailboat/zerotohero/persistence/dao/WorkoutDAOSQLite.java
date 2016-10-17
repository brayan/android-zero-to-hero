package br.com.sailboat.zerotohero.persistence.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.base.BaseDAOSQLite;
import br.com.sailboat.zerotohero.model.Workout;

public class WorkoutDAOSQLite extends BaseDAOSQLite {

    public WorkoutDAOSQLite(Context context) {
        super(context);
    }

    public List<Workout> getAll() {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT Workout.* FROM Workout ");

        return getWorkoutList(query);
    }

    private List<Workout> getWorkoutList(StringBuilder query) {
        Cursor cursor = getReadableDatabase().rawQuery(query.toString(), null);
        List<Workout> workouts = new ArrayList<>();

        while (cursor.moveToNext()) {
            Workout workout = getWorkoutFromCursor(cursor);
            workouts.add(workout);
        }

        cursor.close();

        return workouts;
    }

    private Workout getWorkoutFromCursor(Cursor cursor) {

        Workout workout = new Workout();
        workout.setId(cursor.getLong(cursor.getColumnIndexOrThrow("id")));
        workout.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));

        return workout;
    }

    public long saveAndGetId(Workout workout) throws Exception {
        getWritableDatabase().beginTransactionNonExclusive();
        try {
            StringBuilder sql = new StringBuilder();

            sql.append(" INSERT INTO Workout ");
            sql.append(" (name) ");
            sql.append(" VALUES (?); ");

            SQLiteStatement stmt = getWritableDatabase().compileStatement(sql.toString());
            stmt.bindString(1, workout.getName());

            long id = stmt.executeInsert();

            stmt.clearBindings();

            getWritableDatabase().setTransactionSuccessful();

            return id;
        } finally {
            getWritableDatabase().endTransaction();
        }
    }

    public void update(Workout workout) throws Exception {
        getWritableDatabase().beginTransactionNonExclusive();
        try {
            StringBuilder sql = new StringBuilder();

            sql.append(" UPDATE Workout SET ");
            sql.append(" name = ? ");
            sql.append(" WHERE id = ? ");

            SQLiteStatement stmt = getWritableDatabase().compileStatement(sql.toString());
            stmt.bindString(1, workout.getName());
            stmt.bindLong(2, workout.getId());

            stmt.executeInsert();

            stmt.clearBindings();

            getWritableDatabase().setTransactionSuccessful();

        } finally {
            getWritableDatabase().endTransaction();
        }
    }

    public void delete(long workoutId) {
        getWritableDatabase().beginTransactionNonExclusive();
        try {
            String sql = "DELETE FROM Workout WHERE Workout.id = ?";
            SQLiteStatement stmt = getWritableDatabase().compileStatement(sql);
            stmt.bindLong(1, workoutId);
            stmt.executeUpdateDelete();
            stmt.clearBindings();

            getWritableDatabase().setTransactionSuccessful();
        } finally {
            getWritableDatabase().endTransaction();
        }
    }
}
