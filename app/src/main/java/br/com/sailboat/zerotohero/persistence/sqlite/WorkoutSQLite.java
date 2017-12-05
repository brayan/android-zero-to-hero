package br.com.sailboat.zerotohero.persistence.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.sailboat.canoe.base.BaseSQLite;
import br.com.sailboat.canoe.exception.EntityNotFoundException;
import br.com.sailboat.canoe.helper.StringHelper;
import br.com.sailboat.zerotohero.model.sqlite.Workout;
import br.com.sailboat.zerotohero.persistence.DatabaseOpenHelper;

public class WorkoutSQLite extends BaseSQLite {

    public WorkoutSQLite(SQLiteOpenHelper database) {
        super(database);
    }

    public static WorkoutSQLite newInstance(Context ctx) {
        return new WorkoutSQLite(DatabaseOpenHelper.getInstance(ctx));
    }

    @Override
    public String getCreateTableStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE Workout ( ");
        sb.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" name TEXT NOT NULL, ");
        sb.append(" lastModified TEXT ");
        sb.append(" ); ");

        return sb.toString();
    }

    public Workout getWorkoutById(long workoutId) throws EntityNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT Workout.* FROM Workout ");
        sb.append(" WHERE Workout.id = " + workoutId);

        Cursor cursor = performQuery(sb.toString());

        if (cursor.moveToNext()) {
            Workout workout = buildFromCursor(cursor);
            cursor.close();
            return workout;
        }

        throw new EntityNotFoundException();
    }

    public List<Workout> getAll(String search) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT Workout.* FROM Workout ");

        if (StringHelper.isNotEmpty(search)) {
            sb.append(" WHERE Workout.name LIKE '%" + search + "%'" );
        }

        sb.append(" ORDER BY Workout.name COLLATE NOCASE ");

        return getWorkoutList(sb);
    }

    public long save(Workout workout) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO Workout ");
        sb.append(" (name, lastModified) ");
        sb.append(" VALUES (?, ?); ");

        SQLiteStatement statement = compileStatement(sb.toString());
        statement.bindString(1, workout.getName());

        long id = insert(statement);
        workout.setId(id);

        return id;
    }

    public void update(Workout workout) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" UPDATE Workout SET ");
        sb.append(" name = ?, ");
        sb.append(" lastModified = ? ");
        sb.append(" WHERE id = ? ");

        SQLiteStatement statement = compileStatement(sb.toString());
        statement.bindString(1, workout.getName());
        statement.bindString(2, parseCalendarToString(Calendar.getInstance()));
        statement.bindLong(3, workout.getId());

        update(statement);
    }

    public void delete(long workoutId) throws Exception {
        String query = "DELETE FROM Workout WHERE Workout.id = ?";
        SQLiteStatement statement = compileStatement(query);
        statement.bindLong(1, workoutId);

        delete(statement);
    }

    private List<Workout> getWorkoutList(StringBuilder query) {
        Cursor cursor = performQuery(query.toString());
        List<Workout> workouts = new ArrayList<>();

        while (cursor.moveToNext()) {
            Workout workout = buildFromCursor(cursor);
            workouts.add(workout);
        }

        cursor.close();

        return workouts;
    }

    private Workout buildFromCursor(Cursor cursor) {
        Workout workout = new Workout();
        workout.setId(cursor.getLong(cursor.getColumnIndexOrThrow("id")));
        workout.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
        workout.setLastModified(cursor.getString(cursor.getColumnIndexOrThrow("lastModified")));

        return workout;
    }

    public boolean hasWorkoutAdded() {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT Workout.* FROM Workout ");

        Cursor cursor = performQuery(sb.toString());
        boolean hasWorkout = cursor.moveToNext();
        cursor.close();

        return hasWorkout;

    }

}
