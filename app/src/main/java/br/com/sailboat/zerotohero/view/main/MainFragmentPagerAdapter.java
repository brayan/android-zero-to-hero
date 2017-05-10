package br.com.sailboat.zerotohero.view.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.view.exercise.list.ExerciseListFragment;
import br.com.sailboat.zerotohero.view.workout.list.WorkoutListFragment;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    public static final int POSITION_WORKOUT = 0;
    public static final int POSITION_EXERCISE = 1;

    private static final int NUMBER_OF_FRAGMENTS = 2;

    private Context context;
    private SparseArray<Fragment> fragments = new SparseArray<Fragment>();

    public MainFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context.getApplicationContext();
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
                return context.getString(R.string.tab_workouts);
            }
            default: {
                return context.getString(R.string.tab_exercises);
            }
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        fragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public ExerciseListFragment getExerciseListFragment() {
        return ((ExerciseListFragment) fragments.get(POSITION_EXERCISE));
    }

    public WorkoutListFragment getWorkoutListFragment() {
        return ((WorkoutListFragment) fragments.get(POSITION_WORKOUT));
    }

}
