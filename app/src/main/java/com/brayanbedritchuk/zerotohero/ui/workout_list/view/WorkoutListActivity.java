package com.brayanbedritchuk.zerotohero.ui.workout_list.view;

import android.os.Bundle;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.base.BaseActivity;

public class WorkoutListActivity extends BaseActivity {

    private static final String TAG_WORKOUT_LIST_FRAGMENT = "TAG_WORKOUT_LIST_FRAGMENT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        initFragment();
    }

    private void initFragment() {
        WorkoutListFragment fragment = (WorkoutListFragment) getSupportFragmentManager().findFragmentByTag(TAG_WORKOUT_LIST_FRAGMENT);
        if (fragment == null) {
            addFragment(new WorkoutListFragment(), TAG_WORKOUT_LIST_FRAGMENT);
        }
    }

}
