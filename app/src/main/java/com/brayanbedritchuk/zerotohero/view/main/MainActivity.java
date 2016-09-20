package com.brayanbedritchuk.zerotohero.view.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.helper.AnimationHelper;
import com.brayanbedritchuk.zerotohero.view.exercise.list.ExerciseListFragment;
import com.brayanbedritchuk.zerotohero.view.workout.list.WorkoutListFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainFragmentPagerAdapter adapter;
    private FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator_appbar_tabs_viewpager_fab);
        initViews();
    }

    private void initViews() {
        inflateViews();
        initToolbar();
        initViewPager();
        initListeners();
    }

    private void inflateViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void initToolbar() {
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }

    private void initViewPager() {
        adapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickFab();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    AnimationHelper.performScaleUpAnimation(fab);

                } else if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    AnimationHelper.performScaleDownAnimation(fab);
                }
            }
        });
    }

    private void onClickFab() {

        switch (getCurrentTabPosition()) {
            case MainFragmentPagerAdapter.POSITION_WORKOUT: {
                getWorkoutListFragment().onClickFab();
                return;
            }
            case MainFragmentPagerAdapter.POSITION_EXERCISE: {
                getExerciseListFragment().onClickFab();
                return;
            }
        }
    }

    private ExerciseListFragment getExerciseListFragment() {
        int position = MainFragmentPagerAdapter.POSITION_EXERCISE;
        return ((ExerciseListFragment) adapter.getRegisteredFragment(position));
    }

    private WorkoutListFragment getWorkoutListFragment() {
        int position = MainFragmentPagerAdapter.POSITION_WORKOUT;
        return ((WorkoutListFragment) adapter.getRegisteredFragment(position));
    }

    private int getCurrentTabPosition() {
        return tabLayout.getSelectedTabPosition();
    }

}
