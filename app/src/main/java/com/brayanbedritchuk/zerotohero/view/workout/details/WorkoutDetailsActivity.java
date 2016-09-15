package com.brayanbedritchuk.zerotohero.view.workout.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;

import com.brayanbedritchuk.zerotohero.R;
import com.brayanbedritchuk.zerotohero.base.BaseActivity;
import com.brayanbedritchuk.zerotohero.helper.Extras;
import com.brayanbedritchuk.zerotohero.model.Workout;

public class WorkoutDetailsActivity extends BaseActivity {

    public static void start(Activity activity, Workout workout) {
        Intent starter = new Intent(activity, WorkoutDetailsActivity.class);
        starter.putExtra(Extras.WORKOUT, workout);

        Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle();
        ActivityCompat.startActivity(activity, starter, options);
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
