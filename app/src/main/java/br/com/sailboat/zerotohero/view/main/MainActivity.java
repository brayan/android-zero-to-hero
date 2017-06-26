package br.com.sailboat.zerotohero.view.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.com.sailboat.canoe.view.info.InfoActivity;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.InfoHelper;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_info: {
                startInfoScreen();
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    private void startInfoScreen() {
        InfoActivity.start(this, InfoHelper.getInfo(this));
    }

    private void initViews() {
        initToolbar();
        initViewPager();
        initFab();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }

    private void initViewPager() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setViewPagerListener();
    }

    private void setViewPagerListener() {
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
//                    AnimationHelper.performScaleUpAnimation(fab);
                    fab.show();

                } else if (state == ViewPager.SCROLL_STATE_DRAGGING) {
//                    AnimationHelper.performScaleDownAnimation(fab);
                    fab.hide();
                }
            }
        });
    }

    private void initFab() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
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

    private int getCurrentTabPosition() {
        return tabLayout.getSelectedTabPosition();
    }

    public FloatingActionButton getFab() {
        return this.fab;
    }

}
