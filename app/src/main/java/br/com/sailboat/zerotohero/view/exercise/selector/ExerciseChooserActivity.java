package br.com.sailboat.zerotohero.view.exercise.selector;

import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

import br.com.sailboat.canoe.base.BaseActivitySingleFragment;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;

public class ExerciseChooserActivity extends BaseActivitySingleFragment<ExerciseChooserFragment> {

    public static void start(Fragment fragment, ArrayList<Exercise> exercises, int requestCode) {
        Intent starter = new Intent(fragment.getActivity(), ExerciseChooserActivity.class);
        Extras.putExercises(exercises, starter);
        fragment.startActivityForResult(starter, requestCode);
    }

    @Override
    protected ExerciseChooserFragment newFragmentInstance() {
        return new ExerciseChooserFragment();
    }
}
