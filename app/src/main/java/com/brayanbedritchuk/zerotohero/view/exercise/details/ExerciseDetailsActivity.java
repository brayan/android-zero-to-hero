package com.brayanbedritchuk.zerotohero.view.exercise.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.brayanbedritchuk.zerotohero.base.BaseActivity;
import com.brayanbedritchuk.zerotohero.helper.ExtrasHelper;
import com.brayanbedritchuk.zerotohero.model.Exercise;

public class ExerciseDetailsActivity extends BaseActivity<ExerciseDetailsFragment> {

    public static void start(Fragment fragment, Exercise exercise, int requestCode) {
        Intent starter = getStartIntent(fragment.getActivity(), exercise);
        Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(fragment.getActivity()).toBundle();
        fragment.startActivityForResult(starter, requestCode, options);
    }

    @NonNull
    private static Intent getStartIntent(Context context, Exercise exercise) {
        Intent starter = new Intent(context, ExerciseDetailsActivity.class);
        ExtrasHelper.putExercise(exercise, starter);
        return starter;
    }

    @Override
    protected ExerciseDetailsFragment newFragmentInstance() {
        return new ExerciseDetailsFragment();
    }
}
