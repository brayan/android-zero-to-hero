package com.brayanbedritchuk.zerotohero.view.exercise.list;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.brayanbedritchuk.zerotohero.R;

public class ExerciseListActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);
        verifyAndAddFragment(savedInstanceState);
    }

    private void verifyAndAddFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            addFragment();
        }
    }

    private void addFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame_layout, new ExerciseListFragment());
        ft.commit();
    }

}
