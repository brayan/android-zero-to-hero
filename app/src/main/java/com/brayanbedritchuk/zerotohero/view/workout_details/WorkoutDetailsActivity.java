package com.brayanbedritchuk.zerotohero.view.workout_details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.model.Workout;

public class WorkoutDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_WORKOUT = "EXTRA_WORKOUT";

    public static void start(Activity activity, Workout workout) {
        Intent starter = new Intent(activity, WorkoutDetailsActivity.class);
        starter.putExtra(EXTRA_WORKOUT, workout);

        Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle();
        ActivityCompat.startActivity(activity, starter, options);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_frame);
        verifyAndAddFragment(savedInstanceState);
    }

    private void verifyAndAddFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            addWorkoutDetailsFragment();
        }
    }

    private void addWorkoutDetailsFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.activity_main__fl__fragment, new WorkoutDetailsFragment());
        ft.commit();
    }

}
