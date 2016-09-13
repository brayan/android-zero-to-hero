package com.brayanbedritchuk.zerotohero.persistence.dao;

import android.content.Context;
import android.database.Cursor;

import com.brayanbedritchuk.zerotohero.base.BaseDAOSQLite;
import com.brayanbedritchuk.zerotohero.model.Exercise;
import com.brayanbedritchuk.zerotohero.persistence.table.ExerciseTable;

import java.util.ArrayList;
import java.util.List;

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

}
