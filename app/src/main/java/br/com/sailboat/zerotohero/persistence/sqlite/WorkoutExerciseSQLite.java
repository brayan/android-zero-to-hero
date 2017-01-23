package br.com.sailboat.zerotohero.persistence.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;

import br.com.sailboat.zerotohero.base.BaseSQLite;

public class WorkoutExerciseSQLite extends BaseSQLite {

    public WorkoutExerciseSQLite(Context context) {
        super(context);
    }

    @Override
    public String getQueryCreateTable() {
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE WorkoutExercise ( ");
        sb.append(" workoutId INTEGER, ");
        sb.append(" exerciseId INTEGER, ");
        sb.append(" PRIMARY KEY(workoutId, exerciseId), ");
        sb.append(" FOREIGN KEY(workoutId) REFERENCES Workout(id),");
        sb.append(" FOREIGN KEY(exerciseId) REFERENCES Exercise(id) ");
        sb.append(" ); ");

        return  sb.toString();
    }

    public long save(long workoutId, long exerciseId) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO WorkoutExercise ");
        sb.append(" (workoutId, exerciseId) ");
        sb.append(" VALUES (?, ?); ");

        SQLiteStatement statement = compileStatement(sb.toString());
        statement.bindLong(1, workoutId);
        statement.bindLong(2, exerciseId);

        long id = executeInsert(statement);

        return id;
    }

    public void deleteFromWorkout(long workoutId) throws Exception {
        String query = "DELETE FROM WorkoutExercise WHERE WorkoutExercise.workoutId = ?";
        deleteFromID(query, workoutId);
    }

    public void deleteFromExercise(long exerciseId) throws Exception {
        String query = "DELETE FROM WorkoutExercise WHERE WorkoutExercise.exerciseId = ?";
        deleteFromID(query, exerciseId);
    }

    private void deleteFromID(String query, long id) {
        SQLiteStatement statement = getWritableDatabase().compileStatement(query);
        statement.bindLong(1, id);

        executeUpdateOrDelete(statement);
    }

}
