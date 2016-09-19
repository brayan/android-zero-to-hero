package com.brayanbedritchuk.zerotohero.view.exercise.chooser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.brayanbedritchuk.zerotohero.base.BaseActivitySingleFragment;
import com.brayanbedritchuk.zerotohero.helper.ExtrasHelper;
import com.brayanbedritchuk.zerotohero.model.Exercise;

import java.util.ArrayList;

public class ExerciseChooserActivity extends BaseActivitySingleFragment<ExerciseChooserFragment> {

    public static void start(Fragment fragment, ArrayList<Exercise> exercises, int requestCode) {
        Intent starter = new Intent(fragment.getActivity(), ExerciseChooserActivity.class);
        ExtrasHelper.putExercises(exercises, starter);

        Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(fragment.getActivity()).toBundle();
        fragment.startActivityForResult(starter, requestCode, options);
    }

    @Override
    protected ExerciseChooserFragment newFragmentInstance() {
        return new ExerciseChooserFragment();
    }
}
