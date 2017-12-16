package br.com.sailboat.zerotohero.persistence.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.sailboat.canoe.base.BaseSQLite;
import br.com.sailboat.canoe.exception.EntityNotFoundException;
import br.com.sailboat.zerotohero.model.sqlite.ExerciseHistory;
import br.com.sailboat.zerotohero.persistence.DatabaseOpenHelper;

import static android.R.attr.id;

public class ExerciseHistorySQLite extends BaseSQLite {

    public ExerciseHistorySQLite(SQLiteOpenHelper database) {
        super(database);
    }

    public static ExerciseHistorySQLite newInstance(Context ctx) {
        return new ExerciseHistorySQLite(DatabaseOpenHelper.getInstance(ctx));
    }

    @Override
    public String getCreateTableStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE ExerciseHistory ( ");
        sb.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" exerciseId INTEGER, ");
        sb.append(" weight REAL, ");
        sb.append(" sets INTEGER, ");
        sb.append(" reps INTEGER, ");
        sb.append(" lastModified TEXT, ");
        sb.append(" FOREIGN KEY(exerciseId) REFERENCES Exercise(id) ");
        sb.append(" ); ");

        return  sb.toString();
    }

    public ExerciseHistory getMostRecentExerciseHistory(long exerciseId) throws EntityNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT ExerciseHistory.* FROM ExerciseHistory ");
        sb.append(" WHERE ExerciseHistory.exerciseId = " + exerciseId);
        sb.append(" ORDER BY ExerciseHistory.lastModified DESC");

        Cursor cursor = performQuery(sb.toString());

        if (cursor.moveToNext()) {
            ExerciseHistory exercise = buildFromCursor(cursor);
            cursor.close();
            return exercise;
        }

        throw new EntityNotFoundException();
    }

    public List<ExerciseHistory> getMostRecentByExercise(long exerciseId) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT ExerciseHistory.* FROM ExerciseHistory ");
        sb.append(" WHERE ExerciseHistory.exerciseId = " + exerciseId);
        sb.append(" ORDER BY ExerciseHistory.lastModified DESC");

        return getList(sb.toString());
    }

    public void save(ExerciseHistory history) {
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO ExerciseHistory ");
        sb.append(" (exerciseId, weight, sets, reps, lastModified) ");
        sb.append(" VALUES (?, ?, ?, ?, ?); ");

        SQLiteStatement statement = compileStatement(sb.toString());
        statement.bindLong(1, history.getExerciseId());
        statement.bindDouble(2, history.getWeight());
        statement.bindLong(3, history.getSets());
        statement.bindLong(4, history.getReps());
        statement.bindString(5, parseCalendarToString(Calendar.getInstance()));

        insert(statement);
    }

    public void deleteFromExercise(long exerciseId) throws Exception {
        String query = "DELETE FROM ExerciseHistory WHERE ExerciseHistory.exerciseId = ?";
        SQLiteStatement statement = compileStatement(query);
        statement.bindLong(1, exerciseId);

        delete(statement);
    }

    @NonNull
    private List<ExerciseHistory> getList(String query) throws Exception {
        Cursor cursor = performQuery(query.toString());
        List<ExerciseHistory> tasks = new ArrayList<>();

        while (cursor.moveToNext()) {
            ExerciseHistory task = buildFromCursor(cursor);
            tasks.add(task);
        }

        cursor.close();

        return tasks;
    }

    private ExerciseHistory buildFromCursor(Cursor cursor) {
        ExerciseHistory history = new ExerciseHistory();
        history.setId(cursor.getLong(cursor.getColumnIndexOrThrow("id")));
        history.setExerciseId(cursor.getLong(cursor.getColumnIndexOrThrow("exerciseId")));
        history.setWeight(cursor.getDouble(cursor.getColumnIndexOrThrow("weight")));
        history.setSets(cursor.getInt(cursor.getColumnIndexOrThrow("sets")));
        history.setReps(cursor.getInt(cursor.getColumnIndexOrThrow("reps")));
        history.setLastModified(cursor.getString(cursor.getColumnIndexOrThrow("lastModified")));

        return history;
    }

}
