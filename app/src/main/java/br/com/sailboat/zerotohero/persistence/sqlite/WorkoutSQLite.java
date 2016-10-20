package br.com.sailboat.zerotohero.persistence.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.base.BaseSQLite;
import br.com.sailboat.zerotohero.model.Workout;

public class WorkoutSQLite extends BaseSQLite {

    public WorkoutSQLite(Context context) {
        super(context);
    }

    @Override
    public String getQueryCreateTable() {
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE Workout ( ");
        sb.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" name TEXT NOT NULL ");
        sb.append(" ); ");

        return sb.toString();
    }

    public List<Workout> getAll() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT Workout.* FROM Workout ");

        return getWorkoutList(sb);
    }

    public long saveAndGetId(Workout workout) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO Workout ");
        sb.append(" (name) ");
        sb.append(" VALUES (?); ");

        SQLiteStatement statement = compileStatement(sb.toString());
        statement.bindString(1, workout.getName());

        long id = executeInsert(statement);

        return id;
    }

    public void update(Workout workout) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" UPDATE Workout SET ");
        sb.append(" name = ? ");
        sb.append(" WHERE id = ? ");

        SQLiteStatement statement = compileStatement(sb.toString());
        statement.bindString(1, workout.getName());
        statement.bindLong(2, workout.getId());

        executeUpdateOrDelete(statement);
    }

    public void delete(long workoutId) throws Exception {
        String query = "DELETE FROM Workout WHERE Workout.id = ?";
        SQLiteStatement statement = compileStatement(query);
        statement.bindLong(1, workoutId);

        executeUpdateOrDelete(statement);
    }

    private List<Workout> getWorkoutList(StringBuilder query) {
        Cursor cursor = performQuery(query.toString());
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
