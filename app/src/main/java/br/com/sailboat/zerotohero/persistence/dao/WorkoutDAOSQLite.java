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
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT Workout.* FROM Workout ");

        return getWorkoutList(sb);
    }

    public long saveAndGetId(Workout workout) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO Workout ");
        sb.append(" (name) ");
        sb.append(" VALUES (?); ");

        SQLiteStatement statement = getWritableDatabase().compileStatement(sb.toString());
        statement.bindString(1, workout.getName());

        long id = executeInsert(statement);

        return id;
    }

    public void update(Workout workout) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" UPDATE Workout SET ");
        sb.append(" name = ? ");
        sb.append(" WHERE id = ? ");

        SQLiteStatement statement = getWritableDatabase().compileStatement(sb.toString());
        statement.bindString(1, workout.getName());
        statement.bindLong(2, workout.getId());

        executeInsert(statement);
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
}
