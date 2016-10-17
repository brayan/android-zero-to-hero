package br.com.sailboat.zerotohero.helper;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.zerotohero.base.BaseSQLiteTable;
import br.com.sailboat.zerotohero.persistence.table.ExerciseTable;
import br.com.sailboat.zerotohero.persistence.table.WorkoutExerciseTable;
import br.com.sailboat.zerotohero.persistence.table.WorkoutTable;

public class CreateTablesHelper {

    private SQLiteDatabase database;
    private List<BaseSQLiteTable> tableList;

    public static void createTables(SQLiteDatabase database) throws Exception {
        new CreateTablesHelper(database).createTables();
    }

    private CreateTablesHelper(SQLiteDatabase database) {
        setDatabase(database);
        initTablesList();
    }

    private void createTables() {
        for (br.com.sailboat.zerotohero.base.BaseSQLiteTable BaseSQLiteTable : getTableList()) {
            getDatabase().execSQL(BaseSQLiteTable.getSqlCreateTable());
        }
    }

    private void initTablesList() {
        setTableList(new ArrayList<BaseSQLiteTable>());
        getTableList().add(new WorkoutTable());
        getTableList().add(new ExerciseTable());
        getTableList().add(new WorkoutExerciseTable());
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    public List<BaseSQLiteTable> getTableList() {
        return tableList;
    }

    public void setTableList(List<BaseSQLiteTable> tableList) {
        this.tableList = tableList;
    }
}
