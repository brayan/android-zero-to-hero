package br.com.sailboat.zerotohero.view.ui.workout.insert;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import br.com.sailboat.zerotohero.base.BaseActivity;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.helper.RequestCodes;

public class InsertWorkoutActivity extends BaseActivity<InsertWorkoutFragment> {

    public static void start(Fragment fragment) {
        start(fragment, -1);
    }

    public static void start(Fragment fragment, long workoutId) {
        Intent starter = getStartIntent(fragment.getActivity(), workoutId);
        fragment.startActivityForResult(starter, RequestCodes.INSERT_WORKOUT);
    }

    @NonNull
    private static Intent getStartIntent(Context context, long workoutId) {
        Intent starter = new Intent(context, InsertWorkoutActivity.class);

        if (workoutId != -1) {
            Extras.putWorkoutId(workoutId, starter);
        }

        return starter;
    }

    @Override
    protected InsertWorkoutFragment newFragmentInstance() {
        return new InsertWorkoutFragment();
    }
}
