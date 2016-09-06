package com.brayanbedritchuk.zerotohero.view.new_workout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.brayanbedritchuk.zerotohero.R;

public class NewWorkoutActivity extends AppCompatActivity {

    public static void start(Activity activity) {
        Intent starter = new Intent(activity, NewWorkoutActivity.class);
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
            addNewWorkoutFragment();
        }
    }

    private void addNewWorkoutFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.activity_main__fl__fragment, new NewWorkoutFragment());
        ft.commit();
    }

}
