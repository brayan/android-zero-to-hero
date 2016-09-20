package com.brayanbedritchuk.zerotohero.view.exercise.insert_or_edit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.brayanbedritchuk.zerotohero.base.BaseActivitySingleFragment;
import com.brayanbedritchuk.zerotohero.helper.ExtrasHelper;
import com.brayanbedritchuk.zerotohero.model.Exercise;

public class InsertOrEditExerciseActivity extends BaseActivitySingleFragment<InsertOrEditExerciseFragment> {

    public static void start(Fragment fragment, int requestCode) {
        start(fragment, null, requestCode);
    }

    public static void start(Fragment fragment, Exercise exercise, int requestCode) {
        Intent starter = getStartIntent(fragment.getActivity(), exercise);
        fragment.startActivityForResult(starter, requestCode, getOptions(fragment.getActivity()));
    }

    @NonNull
    private static Intent getStartIntent(Context context, Exercise exercise) {
        Intent starter = new Intent(context, InsertOrEditExerciseActivity.class);

        if (exercise != null) {
            ExtrasHelper.putExercise(exercise, starter);
        }

        return starter;
    }

    private static Bundle getOptions(Activity activity) {
        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle();
    }

    @Override
    protected InsertOrEditExerciseFragment newFragmentInstance() {
        return new InsertOrEditExerciseFragment();
    }
}
