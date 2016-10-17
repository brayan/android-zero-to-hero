package com.brayanbedritchuk.zerotohero.view.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.view.exercise.list.ExerciseListFragment;
import com.brayanbedritchuk.zerotohero.view.workout.list.WorkoutListFragment;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    public static final int POSITION_WORKOUT = 0;
    public static final int POSITION_EXERCISE = 1;

    private static final int NUMBER_OF_FRAGMENTS = 2;

    private Context context;
    private SparseArray<Fragment> fragments;

    public MainFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        setContext(context.getApplicationContext());
        setFragments(new SparseArray<Fragment>());
    }

    @Override
    public int getCount() {
        return NUMBER_OF_FRAGMENTS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case POSITION_WORKOUT: {
                return new WorkoutListFragment();
            }
            default: {
                return new ExerciseListFragment();
            }
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case POSITION_WORKOUT: {
                return getContext().getString(R.string.tab_workouts);
            }
            default: {
                return getContext().getString(R.string.tab_exercises);
            }
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        getFragments().put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        getFragments().remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return getFragments().get(position);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public SparseArray<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(SparseArray<Fragment> fragments) {
        this.fragments = fragments;
    }
}
