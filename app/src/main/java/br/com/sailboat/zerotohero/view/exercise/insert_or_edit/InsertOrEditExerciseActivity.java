package br.com.sailboat.zerotohero.view.exercise.insert_or_edit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import br.com.sailboat.zerotohero.base.BaseActivity;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.model.Exercise;

public class InsertOrEditExerciseActivity extends BaseActivity<InsertOrEditExerciseFragment> {

    public static void start(Fragment fragment, int requestCode) {
        start(fragment, null, requestCode);
    }

    public static void start(Fragment fragment, Exercise exercise, int requestCode) {
        Intent starter = getStartIntent(fragment.getActivity(), exercise);
        fragment.startActivityForResult(starter, requestCode);
    }

    @NonNull
    private static Intent getStartIntent(Context context, Exercise exercise) {
        Intent starter = new Intent(context, InsertOrEditExerciseActivity.class);

        if (exercise != null) {
            ExtrasHelper.putExercise(exercise, starter);
        }

        return starter;
    }

    @Override
    protected InsertOrEditExerciseFragment newFragmentInstance() {
        return new InsertOrEditExerciseFragment();
    }
}
