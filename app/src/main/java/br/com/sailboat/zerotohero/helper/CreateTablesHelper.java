package br.com.sailboat.zerotohero.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.base.BaseSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutExerciseSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutSQLite;

public class CreateTablesHelper {

    private Context context;
    private SQLiteDatabase database;
    private List<BaseSQLite> tableList;

    public static void createTables(Context context, SQLiteDatabase database) {
        new CreateTablesHelper(context, database).createTables();
    }

    private CreateTablesHelper(Context context, SQLiteDatabase database) {
        setContext(context.getApplicationContext());
        setDatabase(database);
        initTableList();
    }

    private void createTables() {
        for (BaseSQLite table : getTableList()) {
            getDatabase().execSQL(table.getQueryCreateTable());
        }
    }

    private void initTableList() {
        setTableList(new ArrayList<BaseSQLite>());
        getTableList().add(new WorkoutSQLite(getContext()));
        getTableList().add(new ExerciseSQLite(getContext()));
        getTableList().add(new WorkoutExerciseSQLite(getContext()));
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    public List<BaseSQLite> getTableList() {
        return tableList;
    }

    public void setTableList(List<BaseSQLite> tableList) {
        this.tableList = tableList;
    }
}
