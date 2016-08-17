package com.brayanbedritchuk.zerotohero.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.brayanbedritchuk.zerotohero.workout_list.view.WorkoutListFragment;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new WorkoutListFragment();
        } else {
            return new WorkoutListFragment();
        }
    }

}
