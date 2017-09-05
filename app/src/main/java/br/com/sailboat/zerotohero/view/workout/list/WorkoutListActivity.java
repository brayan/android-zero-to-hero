package br.com.sailboat.zerotohero.view.workout.list;


import android.content.Intent;
import android.support.v4.app.Fragment;

import br.com.sailboat.canoe.base.BaseActivitySingleFragment;

public class WorkoutListActivity extends BaseActivitySingleFragment<WorkoutListFragment> {


    public static void start(Fragment fragment) {
        Intent intent = new Intent(fragment.getActivity(), WorkoutListActivity.class);
        fragment.startActivity(intent);
    }


    @Override
    protected WorkoutListFragment newFragmentInstance() {
        return new WorkoutListFragment();
    }

}
