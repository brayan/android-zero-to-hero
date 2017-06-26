package br.com.sailboat.zerotohero.view.exercise.list;

import br.com.sailboat.canoe.base.BaseActivitySingleFragment;
import br.com.sailboat.zerotohero.R;

public class ExerciseListActivity extends BaseActivitySingleFragment<ExerciseListFragment> {


    @Override
    protected int getLayoutId() {
        return R.layout.act_exercise_list;
    }

    @Override
    protected ExerciseListFragment newFragmentInstance() {
        return new ExerciseListFragment();
    }
}
