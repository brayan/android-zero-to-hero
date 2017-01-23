package br.com.sailboat.zerotohero.view.ui.workout.list;

import br.com.sailboat.zerotohero.base.BaseActivity;

public class WorkoutListActivity extends BaseActivity<WorkoutListFragment> {

    @Override
    protected WorkoutListFragment newFragmentInstance() {
        return new WorkoutListFragment();
    }

}
