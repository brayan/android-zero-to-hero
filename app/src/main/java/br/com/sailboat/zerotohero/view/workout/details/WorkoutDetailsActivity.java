package br.com.sailboat.zerotohero.view.workout.details;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import br.com.sailboat.canoe.base.BaseActivitySingleFragment;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.helper.RequestCodeHelper;

public class WorkoutDetailsActivity extends BaseActivitySingleFragment<WorkoutDetailsFragment> {

    public static void start(Fragment fragment, long workoutId) {
        Intent starter = getStartIntent(fragment.getActivity(), workoutId);
        fragment.startActivityForResult(starter, RequestCodeHelper.WORKOUT_DETAILS);
    }

    @NonNull
    private static Intent getStartIntent(Context context, long workoutId) {
        Intent starter = new Intent(context, WorkoutDetailsActivity.class);
        ExtrasHelper.putWorkoutId(workoutId, starter);
        return starter;
    }

    @Override
    protected WorkoutDetailsFragment newFragmentInstance() {
        long workoutId = ExtrasHelper.getWorkoutId(getIntent());
        return WorkoutDetailsFragment.newInstance(workoutId);
    }

}
