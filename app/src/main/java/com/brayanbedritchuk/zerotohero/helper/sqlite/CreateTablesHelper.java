package com.brayanbedritchuk.zerotohero.helper.sqlite;

import android.database.sqlite.SQLiteDatabase;

import com.brayanbedritchuk.zerotohero.base.BaseSQLiteTable;
import com.brayanbedritchuk.zerotohero.persistence.table.ExerciseTable;
import com.brayanbedritchuk.zerotohero.persistence.table.WorkoutExerciseTable;
import com.brayanbedritchuk.zerotohero.persistence.table.WorkoutTable;

import java.util.ArrayList;
import java.util.List;



public class CreateTablesHelper {

    private SQLiteDatabase database;
    private List<BaseSQLiteTable> tablesList;

    public static void createTables(SQLiteDatabase database) throws Exception {
        new CreateTablesHelper(database).createTables();
    }

    private CreateTablesHelper(SQLiteDatabase database) {
        setDatabase(database);
        initTablesList();
    }

    private void createTables() {
        for (BaseSQLiteTable BaseSQLiteTable : getTablesList()) {
            getDatabase().execSQL(BaseSQLiteTable.getSqlCreateTable());
        }
    }

    private void initTablesList() {
        this.tablesList = new ArrayList<>();
        this.tablesList.add(new WorkoutTable());
        this.tablesList.add(new ExerciseTable());
        this.tablesList.add(new WorkoutExerciseTable());
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    public List<BaseSQLiteTable> getTablesList() {
        return tablesList;
    }

    public void setTablesList(List<BaseSQLiteTable> tablesList) {
        this.tablesList = tablesList;
    }
}
