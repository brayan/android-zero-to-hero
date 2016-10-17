package br.com.sailboat.zerotohero.view.exercise.details;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import br.com.sailboat.zerotohero.base.BaseActivity;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.model.Exercise;

public class ExerciseDetailsActivity extends BaseActivity<ExerciseDetailsFragment> {

    public static void start(Fragment fragment, Exercise exercise, int requestCode) {
        Intent starter = getStartIntent(fragment.getActivity(), exercise);
        fragment.startActivityForResult(starter, requestCode);
    }

    @NonNull
    private static Intent getStartIntent(Context context, Exercise exercise) {
        Intent starter = new Intent(context, ExerciseDetailsActivity.class);
        ExtrasHelper.putExercise(exercise, starter);
        return starter;
    }

    @Override
    protected ExerciseDetailsFragment newFragmentInstance() {
        return new ExerciseDetailsFragment();
    }
}
