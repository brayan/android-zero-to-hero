package br.com.sailboat.zerotohero.view.workout.list;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import br.com.sailboat.canoe.base.BaseActivitySingleFragment;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;

public class WorkoutListActivity extends BaseActivitySingleFragment<WorkoutListFragment> {


    public static void start(Activity activity) {
        Intent intent = new Intent(activity, WorkoutListActivity.class);
        activity.startActivity(intent);
    }

    public static void start(Fragment fragment) {
        Intent intent = new Intent(fragment.getActivity(), WorkoutListActivity.class);
        fragment.startActivity(intent);
    }

    public static void startFromMenu(Fragment fragment) {
        Intent intent = new Intent(fragment.getActivity(), WorkoutListActivity.class);
        ExtrasHelper.putStartedFromMenu(true, intent);
        fragment.startActivity(intent);
    }


    @Override
    protected WorkoutListFragment newFragmentInstance() {
        return new WorkoutListFragment();
    }

}
