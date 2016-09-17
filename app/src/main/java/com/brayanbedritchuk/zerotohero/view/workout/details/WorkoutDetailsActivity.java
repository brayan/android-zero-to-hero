package com.brayanbedritchuk.zerotohero.view.workout.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.base.BaseActivity;
import com.brayanbedritchuk.zerotohero.helper.ExtrasHelper;
import com.brayanbedritchuk.zerotohero.model.Workout;

public class WorkoutDetailsActivity extends BaseActivity {

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);

        if (savedInstanceState == null) {
            addFragment(R.id.frame_layout, new WorkoutDetailsFragment());
        }
    }

}
