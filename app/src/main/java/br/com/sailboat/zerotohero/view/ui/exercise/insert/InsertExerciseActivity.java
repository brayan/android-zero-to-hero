package br.com.sailboat.zerotohero.view.ui.exercise.insert;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import br.com.sailboat.zerotohero.base.BaseActivity;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.helper.RequestCodes;
import br.com.sailboat.zerotohero.model.Exercise;

public class InsertExerciseActivity extends BaseActivity<InsertExerciseFragment> {

    public static void start(Fragment fragment) {
        start(fragment, null);
    }

    public static void start(Fragment fragment, Exercise exercise) {
        Intent starter = getStartIntent(fragment.getActivity(), exercise);
        fragment.startActivityForResult(starter, RequestCodes.INSERT_EXERCISE);
    }

    @NonNull
    private static Intent getStartIntent(Context context, Exercise exercise) {
        Intent starter = new Intent(context, InsertExerciseActivity.class);

        if (exercise != null) {
            Extras.putExercise(exercise, starter);
        }

        return starter;
    }

    @Override
    protected InsertExerciseFragment newFragmentInstance() {
        return new InsertExerciseFragment();
    }
}
