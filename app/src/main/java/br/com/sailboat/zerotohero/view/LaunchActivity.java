package br.com.sailboat.zerotohero.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import br.com.sailboat.canoe.helper.LogHelper;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutSQLite;
import br.com.sailboat.zerotohero.view.exercise.list.ExerciseListActivity;
import br.com.sailboat.zerotohero.view.workout.list.WorkoutListActivity;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            boolean hasWorkoutAdded = WorkoutSQLite.newInstance(this).hasWorkoutAdded();

            if (hasWorkoutAdded) {
                WorkoutListActivity.start(this);
            } else {
                ExerciseListActivity.start(this);
            }

        } catch (Exception e) {
            LogHelper.logException(e);
            ExerciseListActivity.start(this);
        }

        finish();
    }

}
