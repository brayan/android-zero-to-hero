package br.com.sailboat.zerotohero.persistence.table;

import br.com.sailboat.zerotohero.base.BaseSQLiteTable;

public class WorkoutTable extends BaseSQLiteTable {

    @Override
    public String getSqlCreateTable() {
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE Workout ( ");
        sb.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" name TEXT NOT NULL ");
        sb.append(" ); ");

        return sb.toString();
    }

}
