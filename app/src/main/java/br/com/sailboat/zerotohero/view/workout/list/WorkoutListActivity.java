package br.com.sailboat.zerotohero.view.workout.list;


import br.com.sailboat.canoe.base.BaseActivitySingleFragment;

public class WorkoutListActivity extends BaseActivitySingleFragment<WorkoutListFragment> {

    @Override
    protected WorkoutListFragment newFragmentInstance() {
        return new WorkoutListFragment();
    }

}
