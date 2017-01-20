package br.com.sailboat.zerotohero.view.ui.workout.insert;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import br.com.sailboat.zerotohero.base.BaseActivity;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.model.Workout;

public class InsertOrEditWorkoutActivity extends BaseActivity<InsertOrEditWorkoutFragment> {

    public static void start(Fragment fragment, int requestCode) {
        start(fragment, null, requestCode);
    }

    public static void start(Fragment fragment, Workout workout, int requestCode) {
        Intent starter = getStartIntent(fragment.getActivity(), workout);
        fragment.startActivityForResult(starter, requestCode);
    }

    @NonNull
    private static Intent getStartIntent(Context context, Workout workout) {
        Intent starter = new Intent(context, InsertOrEditWorkoutActivity.class);

        if (workout != null) {
            ExtrasHelper.putWorkout(workout, starter);
        }

        return starter;
    }

    @Override
    protected InsertOrEditWorkoutFragment newFragmentInstance() {
        return new InsertOrEditWorkoutFragment();
    }
}
