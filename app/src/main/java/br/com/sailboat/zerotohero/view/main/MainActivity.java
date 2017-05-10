package br.com.sailboat.zerotohero.view.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.sailboat.canoe.helper.AnimationHelper;
import br.com.sailboat.zerotohero.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainFragmentPagerAdapter adapter;
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
        bindListeners();
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

    private void bindListeners() {
        bindListenerToFab();
        bindListenerToViewPager();
    }

    private void bindListenerToFab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (getCurrentTabPosition()) {
                    case MainFragmentPagerAdapter.POSITION_WORKOUT: {
                        adapter.getWorkoutListFragment().onClickFab();
                        return;
                    }
                    case MainFragmentPagerAdapter.POSITION_EXERCISE: {
                        adapter.getExerciseListFragment().onClickFab();
                        return;
                    }
                }
            }
        });
    }

    private void bindListenerToViewPager() {
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
                adapter.getWorkoutListFragment().onClickFab();
                return;
            }
            case MainFragmentPagerAdapter.POSITION_EXERCISE: {
                adapter.getExerciseListFragment().onClickFab();
                return;
            }
        }
    }

    private int getCurrentTabPosition() {
        return tabLayout.getSelectedTabPosition();
    }

}
