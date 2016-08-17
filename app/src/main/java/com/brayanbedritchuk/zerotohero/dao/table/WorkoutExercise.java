package com.brayanbedritchuk.zerotohero.dao.table;

import com.brayanbedritchuk.zerotohero.base.BaseSQLiteTable;

public class WorkoutExercise extends BaseSQLiteTable {

    @Override
    public String getSqlCreateTable() {
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE WorkoutExercise ( ");
        sb.append(" workoutId INTEGER, ");
        sb.append(" exerciseId INTEGER, ");
        sb.append(" insertingDate TEXT, ");
        sb.append(" FOREIGN KEY(workoutId) REFERENCES Workout(id),");
        sb.append(" FOREIGN KEY(exerciseId) REFERENCES Exercise(id) ");
        sb.append(" ); ");
        return  sb.toString();
    }


}
