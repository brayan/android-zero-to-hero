package br.com.sailboat.zerotohero.view.exercise.insert;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import br.com.sailboat.canoe.base.BaseActivitySingleFragment;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.helper.RequestCodeHelper;

public class InsertExerciseActivity extends BaseActivitySingleFragment<InsertExerciseFragment> {

    public static void start(Fragment fragment) {
        start(fragment, -1);
    }

    public static void start(Fragment fragment, long exerciseId) {
        Intent starter = getStartIntent(fragment.getActivity(), exerciseId);
        fragment.startActivityForResult(starter, RequestCodeHelper.INSERT_EXERCISE);
    }

    @Override
    protected InsertExerciseFragment newFragmentInstance() {
        long exerciseId = ExtrasHelper.getExerciseId(getIntent());
        return InsertExerciseFragment.newInstance(exerciseId);
    }

    @NonNull
    private static Intent getStartIntent(Context context, long exerciseId) {
        Intent starter = new Intent(context, InsertExerciseActivity.class);

        if (exerciseId != -1) {
            ExtrasHelper.putExerciseId(exerciseId, starter);
        }

        return starter;
    }

}
