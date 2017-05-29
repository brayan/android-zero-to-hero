package br.com.sailboat.zerotohero.view.workout.insert;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import br.com.sailboat.canoe.base.BaseActivitySingleFragment;
import br.com.sailboat.canoe.helper.EntityHelper;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.helper.RequestCodeHelper;

public class InsertWorkoutActivity extends BaseActivitySingleFragment<InsertWorkoutFragment> {

    public static void start(Fragment fragment) {
        start(fragment, EntityHelper.NO_ID);
    }

    public static void start(Fragment fragment, long workoutId) {
        Intent starter = getStartIntent(fragment.getActivity(), workoutId);
        fragment.startActivityForResult(starter, RequestCodeHelper.INSERT_WORKOUT);
    }

    @NonNull
    private static Intent getStartIntent(Context context, long workoutId) {
        Intent starter = new Intent(context, InsertWorkoutActivity.class);

        if (workoutId != EntityHelper.NO_ID) {
            ExtrasHelper.putWorkoutId(workoutId, starter);
        }

        return starter;
    }

    @Override
    protected InsertWorkoutFragment newFragmentInstance() {
        long workoutId = ExtrasHelper.getWorkoutId(getIntent());
        return InsertWorkoutFragment.newInstance(workoutId);
    }

}
