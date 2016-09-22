package com.brayanbedritchuk.zerotohero.persistence.table;

import com.brayanbedritchuk.zerotohero.base.BaseSQLiteTable;

public class WorkoutExerciseTable extends BaseSQLiteTable {

    @Override
    public String getSqlCreateTable() {
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE WorkoutExercise ( ");
        sb.append(" workoutId INTEGER, ");
        sb.append(" exerciseId INTEGER, ");
        sb.append(" PRIMARY KEY(workoutId, exerciseId), ");
        sb.append(" FOREIGN KEY(workoutId) REFERENCES Workout(id),");
        sb.append(" FOREIGN KEY(exerciseId) REFERENCES Exercise(id) ");
        sb.append(" ); ");
        return  sb.toString();
    }


}
