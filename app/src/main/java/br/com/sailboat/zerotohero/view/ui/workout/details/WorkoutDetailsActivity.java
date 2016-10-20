package br.com.sailboat.zerotohero.view.ui.workout.details;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import br.com.sailboat.zerotohero.base.BaseActivity;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.model.Workout;

public class WorkoutDetailsActivity extends BaseActivity<WorkoutDetailsFragment> {

    public static void start(Fragment fragment, Workout workout, int requestCode) {
        Intent starter = getStartIntent(fragment.getActivity(), workout);
        fragment.startActivityForResult(starter, requestCode);
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
