package br.com.sailboat.zerotohero.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import br.com.sailboat.zerotohero.view.exercise.list.ExerciseListActivity;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // VERIFY IF IT HAS SOME EXERCISE.. IF IT HAS, START MainActivity, otherwise, start exercise list activity;


//        try {
//            boolean hasExerciseAdded = ExerciseSQLite.newInstance(this).hasExerciseAdded();
//
//            if (hasExerciseAdded) {
//                startActivity(new Intent(this, MainActivity.class));
//            } else {
//                startActivity(new Intent(this, ExerciseListActivity.class));
//            }
//
//        } catch (Exception e) {
//            LogHelper.logException(e);
//            startActivity(new Intent(this, MainActivity.class));
//        }
        startActivity(new Intent(this, ExerciseListActivity.class));
        finish();
    }

}
