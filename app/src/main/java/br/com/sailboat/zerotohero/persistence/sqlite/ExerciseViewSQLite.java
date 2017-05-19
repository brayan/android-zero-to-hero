package br.com.sailboat.zerotohero.persistence.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BaseSQLite;
import br.com.sailboat.canoe.helper.StringHelper;
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

    public List<ExerciseView> getAll() throws Exception {
        StringBuilder sb = new StringBuilder();
        createSelect(sb);
        createInnerJoinExerciseHistory(sb);
        createGroupByExerciseId(sb);
        createOrderByName(sb);

        return getExerciseList(sb.toString());
    }

    public List<ExerciseView> getAll(String termoPesquisa) throws Exception {
        StringBuilder sb = new StringBuilder();
        createSelect(sb);
        createInnerJoinExerciseHistory(sb);
        createWhereTermoPesquisa(sb, termoPesquisa);
        createGroupByExerciseId(sb);
        createOrderByName(sb);

        return getExerciseList(sb.toString());
    }

    private void createWhereTermoPesquisa(StringBuilder sb, String termoPesquisa) {
        if (StringHelper.isNotEmpty(termoPesquisa)) {
            sb.append(" WHERE Exercise.name LIKE '%" + termoPesquisa.trim() + "%' ");
        }
    }

    public List<ExerciseView> getFromWorkout(long workoutId) {
        StringBuilder sb = new StringBuilder();
        createSelect(sb);
        createInnerJoinExerciseHistory(sb);
        createInnerJoinWorkoutExercise(workoutId, sb);
        createGroupByExerciseId(sb);
        createOrderByPosition(sb);

        return getExerciseList(sb.toString());
    }

    private void createOrderByName(StringBuilder sb) {
        sb.append(" ORDER BY Exercise.name COLLATE NOCASE ");
    }

    private void createGroupByExerciseId(StringBuilder sb) {
        sb.append(" GROUP BY ExerciseHistory.exerciseId ");
    }

    private void createOrderByPosition(StringBuilder sb) {
        sb.append(" ORDER BY WorkoutExercise.position");
    }

    private void createInnerJoinWorkoutExercise(long workoutId, StringBuilder sb) {
        sb.append(" INNER JOIN WorkoutExercise ");
        sb.append(" ON Exercise.id = WorkoutExercise.exerciseId ");
        sb.append(" WHERE WorkoutExercise.workoutId = " + workoutId);
    }

    private void createInnerJoinExerciseHistory(StringBuilder sb) {
        sb.append(" INNER JOIN ( SELECT * FROM ExerciseHistory ");
        sb.append(" ORDER BY ExerciseHistory.lastModified ASC ) ExerciseHistory ");
        sb.append(" ON ExerciseHistory.exerciseId = Exercise.id ");
    }

    private void createSelect(StringBuilder sb) {
        sb.append(" SELECT Exercise.id AS id, Exercise.name AS name, ");
        sb.append(" ExerciseHistory.weight AS weight, ExerciseHistory.sets AS sets, ");
        sb.append(" ExerciseHistory.reps AS reps FROM Exercise ");
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
