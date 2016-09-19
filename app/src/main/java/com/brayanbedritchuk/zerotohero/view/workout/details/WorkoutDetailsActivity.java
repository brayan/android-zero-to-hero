package com.brayanbedritchuk.zerotohero.view.workout.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.brayanbedritchuk.zerotohero.base.BaseActivitySingleFragment;
import com.brayanbedritchuk.zerotohero.helper.ExtrasHelper;
import com.brayanbedritchuk.zerotohero.model.Workout;

public class WorkoutDetailsActivity extends BaseActivitySingleFragment<WorkoutDetailsFragment> {

    public static void start(Fragment fragment, Workout workout, int requestCode) {
        Intent starter = getStartIntent(fragment.getActivity(), workout);
        Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(fragment.getActivity()).toBundle();
        fragment.startActivityForResult(starter, requestCode, options);
    }

    @NonNull
    private static Intent getStartIntent(Context context, Workout workout) {
        Intent starter = new Intent(context, WorkoutDetailsActivity.class);
        ExtrasHelper.putWorkout(workout, starter);
        return starter;
    }

    @Override
    protected WorkoutDetailsFragment newFragmentInstance() {
        return new WorkoutDetailsFragment();
    }
}
