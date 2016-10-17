package com.brayanbedritchuk.zerotohero.view.workout.insert_or_edit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.brayanbedritchuk.zerotohero.base.BaseActivity;
import com.brayanbedritchuk.zerotohero.helper.ExtrasHelper;
import com.brayanbedritchuk.zerotohero.model.Workout;

public class InsertOrEditWorkoutActivity extends BaseActivity<InsertOrEditWorkoutFragment> {

    public static void start(Fragment fragment, int requestCode) {
        start(fragment, null, requestCode);
    }

    public static void start(Fragment fragment, Workout workout, int requestCode) {
        Intent starter = getStartIntent(fragment.getActivity(), workout);
        fragment.startActivityForResult(starter, requestCode, getOptions(fragment.getActivity()));
    }

    @NonNull
    private static Intent getStartIntent(Context context, Workout workout) {
        Intent starter = new Intent(context, InsertOrEditWorkoutActivity.class);

        if (workout != null) {
            ExtrasHelper.putWorkout(workout, starter);
        }

        return starter;
    }

    private static Bundle getOptions(Activity activity) {
        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle();
    }

    @Override
    protected InsertOrEditWorkoutFragment newFragmentInstance() {
        return new InsertOrEditWorkoutFragment();
    }
}
