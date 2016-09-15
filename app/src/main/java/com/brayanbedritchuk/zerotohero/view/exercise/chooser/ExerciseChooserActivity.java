package com.brayanbedritchuk.zerotohero.view.exercise.chooser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.base.BaseActivity;
import com.brayanbedritchuk.zerotohero.helper.Extras;
import com.brayanbedritchuk.zerotohero.model.Exercise;

import java.util.ArrayList;

public class ExerciseChooserActivity extends BaseActivity {

    public static void start(Fragment fromFragment, ArrayList<Exercise> exercises, int requestCode) {
        FragmentActivity activity = fromFragment.getActivity();

        Intent starter = new Intent(activity, ExerciseChooserActivity.class);
        starter.putExtra(Extras.SELECTED_EXERCISES, exercises);

        Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle();
        activity.startActivityFromFragment(fromFragment, starter, requestCode, options);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);

        if (savedInstanceState == null) {
            addFragment(R.id.frame_layout, new ExerciseChooserFragment());
        }
    }

}
