package com.brayanbedritchuk.zerotohero.persistence.table;


import com.brayanbedritchuk.zerotohero.base.BaseSQLiteTable;

public class ExerciseTable extends BaseSQLiteTable {


    @Override
    public String getSqlCreateTable() {
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE Exercise ( ");
        sb.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" name TEXT, ");
        sb.append(" weight REAL, ");
        sb.append(" set INTEGER, ");
        sb.append(" repetition INTEGER, ");
        sb.append(" insertingDate TEXT ");
        sb.append(" ); ");
        return  sb.toString();
    }
}
