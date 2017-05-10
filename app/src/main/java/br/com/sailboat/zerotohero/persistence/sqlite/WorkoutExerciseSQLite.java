package br.com.sailboat.zerotohero.persistence.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.Calendar;

import br.com.sailboat.canoe.base.BaseSQLite;
import br.com.sailboat.zerotohero.persistence.DatabaseOpenHelper;

public class WorkoutExerciseSQLite extends BaseSQLite {

    public WorkoutExerciseSQLite(SQLiteOpenHelper database) {
        super(database);
    }

    public static WorkoutExerciseSQLite newInstance(Context ctx) {
        return new WorkoutExerciseSQLite(DatabaseOpenHelper.getInstance(ctx));
    }

    @Override
    public String getCreateTableStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE WorkoutExercise ( ");
        sb.append(" workoutId INTEGER, ");
        sb.append(" exerciseId INTEGER, ");
        sb.append(" position INTEGER, ");
        sb.append(" lastModified TEXT, ");
        sb.append(" PRIMARY KEY(workoutId, exerciseId), ");
        sb.append(" FOREIGN KEY(workoutId) REFERENCES Workout(id),");
        sb.append(" FOREIGN KEY(exerciseId) REFERENCES Exercise(id) ");
        sb.append(" ); ");

        return  sb.toString();
    }

    public long save(long workoutId, long exerciseId, long position) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO WorkoutExercise ");
        sb.append(" (workoutId, exerciseId, position, lastModified) ");
        sb.append(" VALUES (?, ?, ?, ?); ");

        SQLiteStatement statement = compileStatement(sb.toString());
        statement.bindLong(1, workoutId);
        statement.bindLong(2, exerciseId);
        statement.bindLong(3, position);
        statement.bindString(4, parseCalendarToString(Calendar.getInstance()));

        long id = insert(statement);

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
        SQLiteStatement statement = compileStatement(query);
        statement.bindLong(1, id);

        delete(statement);
    }

}
