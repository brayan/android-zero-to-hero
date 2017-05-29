package br.com.sailboat.zerotohero.view.exercise.details;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import br.com.sailboat.canoe.base.BaseActivitySingleFragment;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.helper.RequestCodeHelper;

public class ExerciseDetailsActivity extends BaseActivitySingleFragment<ExerciseDetailsFragment> {

    public static void start(Fragment fragment, long exerciseId) {
        Intent starter = getStartIntent(fragment.getActivity(), exerciseId);
        fragment.startActivityForResult(starter, RequestCodeHelper.EXERCISE_DETAILS);
    }

    @NonNull
    private static Intent getStartIntent(Context context, long exerciseId) {
        Intent starter = new Intent(context, ExerciseDetailsActivity.class);
        ExtrasHelper.putExerciseId(exerciseId, starter);
        return starter;
    }

    @Override
    protected ExerciseDetailsFragment newFragmentInstance() {
        long exerciseId = ExtrasHelper.getExerciseId(getIntent());
        return ExerciseDetailsFragment.newInstance(exerciseId);
    }

}
