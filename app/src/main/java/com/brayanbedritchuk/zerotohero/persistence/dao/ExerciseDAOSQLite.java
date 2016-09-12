package com.brayanbedritchuk.zerotohero.persistence.dao;

import android.content.Context;
import android.database.Cursor;

import com.brayanbedritchuk.zerotohero.base.BaseDAOSQLite;
import com.brayanbedritchuk.zerotohero.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDAOSQLite extends BaseDAOSQLite {

    public ExerciseDAOSQLite(Context context) {
        super(context);
    }

    public List<Exercise> getAll() {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT Exercise.* FROM Exercise ");

        return getExerciseList(query);
    }

    private List<Exercise> getExerciseList(StringBuilder query) {
        Cursor cursor = getReadableDatabase().rawQuery(query.toString(), null);
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
        exercise.setId(cursor.getLong(cursor.getColumnIndexOrThrow("id")));
        exercise.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));

        return exercise;
    }

}
