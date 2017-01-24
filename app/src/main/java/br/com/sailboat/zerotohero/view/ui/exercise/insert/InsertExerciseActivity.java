package br.com.sailboat.zerotohero.view.ui.exercise.insert;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import br.com.sailboat.zerotohero.base.BaseActivity;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.helper.RequestCodes;

public class InsertExerciseActivity extends BaseActivity<InsertExerciseFragment> {

    public static void start(Fragment fragment) {
        start(fragment, -1);
    }

    public static void start(Fragment fragment, long exerciseId) {
        Intent starter = getStartIntent(fragment.getActivity(), exerciseId);
        fragment.startActivityForResult(starter, RequestCodes.INSERT_EXERCISE);
    }

    @NonNull
    private static Intent getStartIntent(Context context, long exerciseId) {
        Intent starter = new Intent(context, InsertExerciseActivity.class);

        if (exerciseId != -1) {
            Extras.putExerciseId(exerciseId, starter);
        }

        return starter;
    }

    @Override
    protected InsertExerciseFragment newFragmentInstance() {
        return new InsertExerciseFragment();
    }
}
