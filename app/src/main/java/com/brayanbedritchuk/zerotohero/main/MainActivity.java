package com.brayanbedritchuk.zerotohero.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.brayanbedritchuk.zerotohero.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MainFragmentPagerAdapter pagerAdapter;

    private Toolbar toolbar;

    private TextView tvToolbarTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.addOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                    }
                });

        pagerAdapter =
                new MainFragmentPagerAdapter(
                        getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvToolbarTitle = (TextView) findViewById(R.id.toolbar_title);

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
