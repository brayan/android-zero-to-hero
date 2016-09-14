package com.brayanbedritchuk.zerotohero.view.exercise.chooser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.model.Exercise;

import java.util.ArrayList;

public class ExerciseChooserActivity extends AppCompatActivity {

    public static final String EXTRA_SELECTED_EXERCISES = "EXTRA_SELECTED_EXERCISES";

    public static void start(Activity activity, ArrayList<Exercise> exercises, int requestCode) {
        Intent starter = new Intent(activity, ExerciseChooserActivity.class);
        starter.putExtra(EXTRA_SELECTED_EXERCISES, exercises);

        Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle();
        ActivityCompat.startActivityForResult(activity, starter, requestCode, options);
    }

    public static void start(Fragment fragment, ArrayList<Exercise> exercises, int requestCode) {
        Intent starter = new Intent(fragment.getActivity(), ExerciseChooserActivity.class);
        starter.putExtra(EXTRA_SELECTED_EXERCISES, exercises);

        Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(fragment.getActivity()).toBundle();
        fragment.getActivity().startActivityFromFragment(fragment, starter, requestCode, options);
    }

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
        ft.add(R.id.frame_layout, new ExerciseChooserFragment());
        ft.commit();
    }

}
