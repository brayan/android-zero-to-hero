package br.com.sailboat.zerotohero.persistence.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BaseSQLite;
import br.com.sailboat.zerotohero.model.view.ExerciseView;
import br.com.sailboat.zerotohero.persistence.DatabaseOpenHelper;

public class ExerciseViewSQLite extends BaseSQLite {

    public ExerciseViewSQLite(SQLiteOpenHelper database) {
        super(database);
    }

    public static ExerciseViewSQLite newInstance(Context ctx) {
        return new ExerciseViewSQLite(DatabaseOpenHelper.getInstance(ctx));
    }

    @Override
    public String getCreateTableStatement() {
        return "";
    }

    // TODO: TESTAR MUITO BEM!
    public List<ExerciseView> getAll() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT Exercise.id AS id, Exercise.name AS name, ");
        sb.append(" ExerciseHistory.weight AS weight, ExerciseHistory.sets AS sets, ");
        sb.append(" ExerciseHistory.reps AS reps FROM Exercise ");
        sb.append(" INNER JOIN ( SELECT * FROM ExerciseHistory ");
        sb.append(" ORDER BY ExerciseHistory.lastModified ASC ) ExerciseHistory ");
        sb.append(" ON ExerciseHistory.exerciseId = Exercise.id ");
        sb.append(" GROUP BY ExerciseHistory.exerciseId ");

        return getExerciseList(sb.toString());
    }

    // TODO IMPLEMENTAR
    public List<ExerciseView> getFromWorkout(long workoutId) {
        return new ArrayList<>();
    }

    private List<ExerciseView> getExerciseList(String query) {
        Cursor cursor = performQuery(query);
        List<ExerciseView> exercises = new ArrayList<>();

        while (cursor.moveToNext()) {
            ExerciseView exercise = buildFromCursor(cursor);
            exercises.add(exercise);
        }

        cursor.close();

        return exercises;
    }

    private ExerciseView buildFromCursor(Cursor cursor) {
        ExerciseView exercise = new ExerciseView();
        exercise.setExerciseId(cursor.getLong(cursor.getColumnIndexOrThrow("id")));
        exercise.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
        exercise.setWeight(cursor.getDouble(cursor.getColumnIndexOrThrow("weight")));
        exercise.setSet(cursor.getInt(cursor.getColumnIndexOrThrow("sets")));
        exercise.setRep(cursor.getInt(cursor.getColumnIndexOrThrow("reps")));

        return exercise;
    }



}
