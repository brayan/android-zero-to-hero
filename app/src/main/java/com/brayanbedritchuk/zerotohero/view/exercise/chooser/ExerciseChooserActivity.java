package com.brayanbedritchuk.zerotohero.view.exercise.chooser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.base.BaseActivity;
import com.brayanbedritchuk.zerotohero.helper.Extras;
import com.brayanbedritchuk.zerotohero.model.Exercise;

import java.util.ArrayList;

public class ExerciseChooserActivity extends BaseActivity {

    public static void start(Fragment fragment, ArrayList<Exercise> exercises, int requestCode) {
        Intent starter = new Intent(fragment.getActivity(), ExerciseChooserActivity.class);
        starter.putExtra(Extras.SELECTED_EXERCISES, exercises);

        Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(fragment.getActivity()).toBundle();
        fragment.startActivityForResult(starter, requestCode, options);
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
