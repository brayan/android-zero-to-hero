package com.brayanbedritchuk.zerotohero.view.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.view.workout_list.WorkoutListFragment;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    public static final int WORKOUT_LIST_POSITION = 0;
    public static final int EXERCISE_LIST_POSITION = 1;

    private static final int NUNBER_OF_FRAGMENTS = 2;

    private Context context;
    private SparseArray<Fragment> fragments;

    public MainFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        setContext(context.getApplicationContext());
        setFragments(new SparseArray<Fragment>());
    }

    @Override
    public int getCount() {
        return NUNBER_OF_FRAGMENTS;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case WORKOUT_LIST_POSITION: {
                return new WorkoutListFragment();
            }
            case EXERCISE_LIST_POSITION: {
                return new WorkoutListFragment();
            }
            default: {
                return new WorkoutListFragment();
            }
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case WORKOUT_LIST_POSITION: {
                return getContext().getString(R.string.tab_workouts);
            }
            case EXERCISE_LIST_POSITION: {
                return getContext().getString(R.string.tab_exercises);
            }
            default: {
                return super.getPageTitle(position);
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
