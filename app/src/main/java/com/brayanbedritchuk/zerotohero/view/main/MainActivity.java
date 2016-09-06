package com.brayanbedritchuk.zerotohero.view.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.view.workout_list.WorkoutListFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainFragmentPagerAdapter pagerAdapter;
    private FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
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
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.pager);
        fab = (FloatingActionButton) findViewById(R.id.activity_main_fab);
    }

    private void initToolbar() {
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }

    private void initViewPager() {
        pagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickFab();
            }
        });
    }

    private void onClickFab() {

        switch (getCurrentTabPosition()) {

            case MainFragmentPagerAdapter.WORKOUT_LIST_POSITION: {
                getWorkoutListFragment().onClickFab();
                return;
            }

            case MainFragmentPagerAdapter.EXERCISE_LIST_POSITION: {
                Toast.makeText(MainActivity.this, "Nothing yet", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private WorkoutListFragment getWorkoutListFragment() {
        int position = MainFragmentPagerAdapter.WORKOUT_LIST_POSITION;
        return ((WorkoutListFragment) pagerAdapter.getRegisteredFragment(position));
    }

    private int getCurrentTabPosition() {
        return tabLayout.getSelectedTabPosition();
    }

}
