package br.com.sailboat.zerotohero.view.ui.workout.details;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import br.com.sailboat.zerotohero.base.BaseActivity;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.helper.RequestCodes;

public class WorkoutDetailsActivity extends BaseActivity<WorkoutDetailsFragment> {

    public static void start(Fragment fragment, long workoutId) {
        Intent starter = getStartIntent(fragment.getActivity(), workoutId);
        fragment.startActivityForResult(starter, RequestCodes.WORKOUT_DETAILS);
    }

    @NonNull
    private static Intent getStartIntent(Context context, long workoutId) {
        Intent starter = new Intent(context, WorkoutDetailsActivity.class);
        Extras.putWorkoutId(workoutId, starter);
        return starter;
    }

    @Override
    protected WorkoutDetailsFragment newFragmentInstance() {
        return new WorkoutDetailsFragment();
    }
}
