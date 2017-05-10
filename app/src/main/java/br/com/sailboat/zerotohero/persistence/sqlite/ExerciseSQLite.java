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
import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.persistence.DatabaseOpenHelper;

public class ExerciseSQLite extends BaseSQLite {

    public ExerciseSQLite(SQLiteOpenHelper database) {
        super(database);
    }

    public static ExerciseSQLite newInstance(Context ctx) {
        return new ExerciseSQLite(DatabaseOpenHelper.getInstance(ctx));
    }

    @Override
    public String getCreateTableStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE Exercise ( ");
        sb.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" name TEXT, ");
        sb.append(" lastModified TEXT ");
        sb.append(" ); ");

        return  sb.toString();
    }

    public Exercise getExerciseById(long exerciseId) throws EntityNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT Exercise.* FROM Exercise ");
        sb.append(" WHERE Exercise.id = " + exerciseId);

        Cursor cursor = performQuery(sb.toString());

        if (cursor.moveToNext()) {
            Exercise exercise = buildFromCursor(cursor);
            cursor.close();
            return exercise;
        }

        throw new EntityNotFoundException();
    }

    public List<Exercise> getAll() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT Exercise.* FROM Exercise ");
        sb.append(" ORDER BY Exercise.name COLLATE NOCASE ");

        return getExerciseList(sb.toString());
    }

    public List<Exercise> getFromWorkout(long workoutId) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT Exercise.* FROM Exercise ");
        sb.append(" INNER JOIN WorkoutExercise ");
        sb.append(" ON Exercise.id = WorkoutExercise.exerciseId ");
        sb.append(" WHERE WorkoutExercise.workoutId = " + workoutId);
        sb.append(" ORDER BY WorkoutExercise.position ");

        return getExerciseList(sb.toString());
    }

    public long save(Exercise exercise) throws Exception {
        String stmt = "INSERT INTO Exercise (name, lastModified) VALUES (?, ?);";

        SQLiteStatement statement = compileStatement(stmt);
        bindExerciseToInsertStatement(exercise, statement);

        long id = insert(statement);

        return id;
    }

    public void update(Exercise exercise) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" UPDATE Exercise SET ");
        sb.append(" name = ?, ");
        sb.append(" lastModified = ? ");
        sb.append(" WHERE id = ?; ");

        SQLiteStatement statement = compileStatement(sb.toString());
        bindExerciseToUpdateStatement(exercise, statement);

        update(statement);
    }

    public void delete(long exerciseId) throws Exception {
        String stmt = "DELETE FROM Exercise WHERE Exercise.id = ?;";
        SQLiteStatement statement = compileStatement(stmt);
        statement.bindLong(1, exerciseId);

        delete(statement);
    }

    private List<Exercise> getExerciseList(String query) {
        Cursor cursor = performQuery(query);
        List<Exercise> exercises = new ArrayList<>();

        while (cursor.moveToNext()) {
            Exercise exercise = buildFromCursor(cursor);
            exercises.add(exercise);
        }

        cursor.close();

        return exercises;
    }

    private Exercise buildFromCursor(Cursor cursor) {
        Exercise exercise = new Exercise();
        exercise.setId(cursor.getLong(cursor.getColumnIndexOrThrow("id")));
        exercise.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
        exercise.setLastModified(cursor.getString(cursor.getColumnIndexOrThrow("lastModified")));

        return exercise;
    }

    private void bindExerciseToInsertStatement(Exercise exercise, SQLiteStatement statement) {
        statement.bindString(1, exercise.getName());
        statement.bindString(2, parseCalendarToString(Calendar.getInstance()));
    }

    private void bindExerciseToUpdateStatement(Exercise exercise, SQLiteStatement statement) {
        bindExerciseToInsertStatement(exercise, statement);
        statement.bindString(1, exercise.getName());
        statement.bindString(2, parseCalendarToString(Calendar.getInstance()));
        statement.bindLong(3, exercise.getId());
    }

}
