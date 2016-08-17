package com.brayanbedritchuk.zerotohero.new_workout.view;

import android.os.Bundle;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.base.BaseActivity;

public class NewWorkoutActivity extends BaseActivity {

    private static final String TAG_NEW_WORKOUT_FRAGMENT = "TAG_NEW_WORKOUT_FRAGMENT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }

    private void initFragment() {
        NewWorkoutFragment fragment = (NewWorkoutFragment) getSupportFragmentManager().findFragmentByTag(TAG_NEW_WORKOUT_FRAGMENT);
        if (fragment == null) {
            addFragment(new NewWorkoutFragment(), TAG_NEW_WORKOUT_FRAGMENT);
        }
    }

}
