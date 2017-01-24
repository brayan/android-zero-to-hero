package br.com.sailboat.zerotohero.view.ui.exercise.details;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import br.com.sailboat.zerotohero.base.BaseActivity;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.helper.RequestCodes;

public class ExerciseDetailsActivity extends BaseActivity<ExerciseDetailsFragment> {

    public static void start(Fragment fragment, long exerciseId) {
        Intent starter = getStartIntent(fragment.getActivity(), exerciseId);
        fragment.startActivityForResult(starter, RequestCodes.EXERCISE_DETAILS);
    }

    @NonNull
    private static Intent getStartIntent(Context context, long exerciseId) {
        Intent starter = new Intent(context, ExerciseDetailsActivity.class);
        Extras.putExerciseId(exerciseId, starter);
        return starter;
    }

    @Override
    protected ExerciseDetailsFragment newFragmentInstance() {
        return new ExerciseDetailsFragment();
    }
}
