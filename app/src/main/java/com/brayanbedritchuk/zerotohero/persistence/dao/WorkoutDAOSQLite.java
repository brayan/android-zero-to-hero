package com.brayanbedritchuk.zerotohero.persistence.dao;

import android.content.Context;
import android.database.Cursor;

import com.brayanbedritchuk.zerotohero.base.BaseDAOSQLite;
import com.brayanbedritchuk.zerotohero.model.Workout;

import java.util.ArrayList;
import java.util.List;

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

}
