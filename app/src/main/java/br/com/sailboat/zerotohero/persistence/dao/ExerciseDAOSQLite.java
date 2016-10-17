package br.com.sailboat.zerotohero.persistence.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.base.BaseDAOSQLite;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.persistence.table.ExerciseTable;

public class ExerciseDAOSQLite extends BaseDAOSQLite {

    public ExerciseDAOSQLite(Context context) {
        super(context);
    }

    public List<Exercise> getAll() {
        return getExerciseList(ExerciseTable.createQueryGetAll());
    }

    public List<Exercise> getFromWorkout(long workoutId) {
        return getExerciseList(ExerciseTable.createQueryGetFromWorkout(workoutId));
    }

    private List<Exercise> getExerciseList(String query) {
        Cursor cursor = getReadableDatabase().rawQuery(query, null);
        List<Exercise> exercises = new ArrayList<>();

        while (cursor.moveToNext()) {
            Exercise exercise = ExerciseTable.getExerciseFromCursor(cursor);
            exercises.add(exercise);
        }

        cursor.close();

        return exercises;
    }

    public long saveAndGetId(Exercise exercise) {
        getWritableDatabase().beginTransactionNonExclusive();
        try {
            StringBuilder sql = new StringBuilder();

            sql.append(" INSERT INTO Exercise ");
            sql.append(" (name, weight, exerciseSet, repetition) ");
            sql.append(" VALUES (?, ?, ?, ?); ");

            SQLiteStatement stmt = getWritableDatabase().compileStatement(sql.toString());
            stmt.bindString(1, exercise.getName());
            stmt.bindDouble(2, exercise.getWeight());
            stmt.bindLong(3, exercise.getSet());
            stmt.bindLong(4, exercise.getRepetition());

            long id = stmt.executeInsert();

            stmt.clearBindings();

            getWritableDatabase().setTransactionSuccessful();

            return id;
        } finally {
            getWritableDatabase().endTransaction();
        }
    }

    public void update(Exercise exercise) {
        getWritableDatabase().beginTransactionNonExclusive();
        try {
            StringBuilder sql = new StringBuilder();

            sql.append(" UPDATE Exercise SET ");
            sql.append(" name = ?, ");
            sql.append(" weight = ?, ");
            sql.append(" exerciseSet = ?, ");
            sql.append(" repetition = ? ");
            sql.append(" WHERE id = ? ");

            SQLiteStatement stmt = getWritableDatabase().compileStatement(sql.toString());
            stmt.bindString(1, exercise.getName());
            stmt.bindDouble(2, exercise.getWeight());
            stmt.bindLong(3, exercise.getSet());
            stmt.bindLong(4, exercise.getRepetition());
            stmt.bindLong(5, exercise.getId());

            stmt.executeInsert();

            stmt.clearBindings();

            getWritableDatabase().setTransactionSuccessful();

        } finally {
            getWritableDatabase().endTransaction();
        }
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
}
