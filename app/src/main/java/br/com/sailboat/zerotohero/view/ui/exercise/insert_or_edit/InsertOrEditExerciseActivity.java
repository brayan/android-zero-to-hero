package br.com.sailboat.zerotohero.view.ui.exercise.insert_or_edit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import br.com.sailboat.zerotohero.base.BaseActivity;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.helper.RequestCodes;
import br.com.sailboat.zerotohero.model.Exercise;

public class InsertOrEditExerciseActivity extends BaseActivity<InsertOrEditExerciseFragment> {

    public static void start(Fragment fragment) {
        start(fragment, null);
    }

    public static void start(Fragment fragment, Exercise exercise) {
        Intent starter = getStartIntent(fragment.getActivity(), exercise);
        fragment.startActivityForResult(starter, RequestCodes.INSERT_EXERCISE);
    }

    @NonNull
    private static Intent getStartIntent(Context context, Exercise exercise) {
        Intent starter = new Intent(context, InsertOrEditExerciseActivity.class);

        if (exercise != null) {
            Extras.putExercise(exercise, starter);
        }

        return starter;
    }

    @Override
    protected InsertOrEditExerciseFragment newFragmentInstance() {
        return new InsertOrEditExerciseFragment();
    }
}
