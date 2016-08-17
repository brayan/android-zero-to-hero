package com.brayanbedritchuk.zerotohero.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.workout_list.view.WorkoutListFragment;

public class MainActivity extends AppCompatActivity {

    private static final int WORKOUT_LIST_FRAGMENT_POSITION = 0;
    private static final int EXERCISE_LIST_FRAGMENT_POSITION = 1;

    private ViewPager viewPager;
    private MainFragmentPagerAdapter pagerAdapter;

    private Toolbar toolbar;

    private TextView tvToolbarTitle;
    private TabLayout tab;

    private FloatingActionButton btNewWorkout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab = (TabLayout) findViewById(R.id.tabs);

        viewPager = (ViewPager) findViewById(R.id.pager);

        pagerAdapter =
                new MainFragmentPagerAdapter(
                        getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        tab.setupWithViewPager(viewPager);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvToolbarTitle = (TextView) findViewById(R.id.toolbar_title);

        btNewWorkout = (FloatingActionButton) findViewById(R.id.fragment_workout_list__bt__new_workout);

        btNewWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = tab.getSelectedTabPosition();

                switch (currentPosition) {
                    case WORKOUT_LIST_FRAGMENT_POSITION:
                        ((WorkoutListFragment) pagerAdapter.getRegisteredFragment(WORKOUT_LIST_FRAGMENT_POSITION)).onClickFab();
                        return;

                    case EXERCISE_LIST_FRAGMENT_POSITION:
                        Toast.makeText(MainActivity.this, "Nothing yet", Toast.LENGTH_SHORT).show();
                        return;
                }
            }
        });

        initToolbar();
    }

    private void initToolbar() {
        initAppCompatActivity();
        initToolbarTitle();
    }

    protected void initAppCompatActivity() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initToolbarTitle() {
        tvToolbarTitle.setText("Zero to Hero!");
    }
}
