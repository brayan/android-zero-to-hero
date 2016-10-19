package br.com.sailboat.zerotohero.persistence.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.base.BaseDAOSQLite;
import br.com.sailboat.zerotohero.model.Exercise;

public class ExerciseDAOSQLite extends BaseDAOSQLite {

    public ExerciseDAOSQLite(Context context) {
        super(context);
    }

    public List<Exercise> getAll() {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT Exercise.* FROM Exercise ");

        return getExerciseList(sb.toString());
    }

    public List<Exercise> getFromWorkout(long workoutId) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT Exercise.* FROM Exercise ");
        sb.append(" INNER JOIN WorkoutExercise ");
        sb.append(" ON Exercise.id = WorkoutExercise.exerciseId ");
        sb.append(" WHERE WorkoutExercise.workoutId = " + workoutId);

        return getExerciseList(sb.toString());
    }

    public long saveAndGetId(Exercise exercise) {
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO Exercise ");
        sb.append(" (name, weight, exerciseSet, repetition) ");
        sb.append(" VALUES (?, ?, ?, ?); ");

        SQLiteStatement statement = getWritableDatabase().compileStatement(sb.toString());
        bindExerciseToInsertStatement(exercise, statement);

        long id = executeInsert(statement);

        return id;
    }

    public void update(Exercise exercise) {
        StringBuilder sb = new StringBuilder();
        sb.append(" UPDATE Exercise SET ");
        sb.append(" name = ?, ");
        sb.append(" weight = ?, ");
        sb.append(" exerciseSet = ?, ");
        sb.append(" repetition = ? ");
        sb.append(" WHERE id = ? ");

        SQLiteStatement statement = getWritableDatabase().compileStatement(sb.toString());
        bindExerciseToUpdateStatement(exercise, statement);

        executeUpdate(statement);
    }

    public void delete(long exerciseId) {
        getWritableDatabase().beginTransactionNonExclusive();
        try {
            String sql = "DELETE FROM Exercise WHERE Exercise.id = ?";
            SQLiteStatement stmt = getWritableDatabase().compileStatement(sql);
            stmt.bindLong(1, exerciseId);
            stmt.executeUpdateDelete();
            stmt.clearBindings();

            getWritableDatabase().setTransactionSuccessful();
        } finally {
            getWritableDatabase().endTransaction();
        }
    }

    @NonNull
    private void bindExerciseToInsertStatement(Exercise exercise, SQLiteStatement statement) {
        statement.bindString(1, exercise.getName());
        statement.bindDouble(2, exercise.getWeight());
        statement.bindLong(3, exercise.getSet());
        statement.bindLong(4, exercise.getRepetition());
    }

    private void bindExerciseToUpdateStatement(Exercise exercise, SQLiteStatement statement) {
        bindExerciseToInsertStatement(exercise, statement);
        statement.bindLong(5, exercise.getId());
    }

    private List<Exercise> getExerciseList(String query) {
        Cursor cursor = getReadableDatabase().rawQuery(query, null);
        List<Exercise> exercises = new ArrayList<>();

        while (cursor.moveToNext()) {
            Exercise exercise = getExerciseFromCursor(cursor);
            exercises.add(exercise);
        }

        cursor.close();

        return exercises;
    }

    private Exercise getExerciseFromCursor(Cursor cursor) {
        Exercise exercise = new Exercise();
        exercise.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
        exercise.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
        exercise.setRepetition(cursor.getInt(cursor.getColumnIndexOrThrow("repetition")));
        exercise.setSet(cursor.getInt(cursor.getColumnIndexOrThrow("exerciseSet")));
        exercise.setWeight(cursor.getDouble(cursor.getColumnIndexOrThrow("weight")));

        return exercise;
    }
}
