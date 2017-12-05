package br.com.sailboat.zerotohero.view.exercise.list;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import br.com.sailboat.canoe.base.BaseActivitySingleFragment;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;

public class ExerciseListActivity extends BaseActivitySingleFragment<ExerciseListFragment> {


    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ExerciseListActivity.class);
        activity.startActivity(intent);
    }

    public static void start(Fragment fragment) {
        Intent intent = new Intent(fragment.getActivity(), ExerciseListActivity.class);
        fragment.startActivity(intent);
    }

    public static void startFromMenu(Fragment fragment) {
        Intent intent = new Intent(fragment.getActivity(), ExerciseListActivity.class);
        ExtrasHelper.putStartedFromMenu(true, intent);
        fragment.startActivity(intent);
    }


    @Override
    protected ExerciseListFragment newFragmentInstance() {
        return new ExerciseListFragment();
    }

}
