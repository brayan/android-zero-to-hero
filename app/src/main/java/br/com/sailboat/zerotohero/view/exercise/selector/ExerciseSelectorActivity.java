package br.com.sailboat.zerotohero.view.exercise.selector;

import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.List;

import br.com.sailboat.canoe.base.BaseActivitySingleFragment;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
import br.com.sailboat.zerotohero.helper.RequestCodeHelper;
import br.com.sailboat.zerotohero.model.view.ExerciseView;

public class ExerciseSelectorActivity extends BaseActivitySingleFragment<ExerciseSelectorFragment> {

    public static void start(Fragment fragment, List<ExerciseView> exercises) {
        Intent starter = new Intent(fragment.getActivity(), ExerciseSelectorActivity.class);
        ExtrasHelper.putExerciseViewList(exercises, starter);
        fragment.startActivityForResult(starter, RequestCodeHelper.EXERCISE_SELECTOR);
    }

    @Override
    protected ExerciseSelectorFragment newFragmentInstance() {
        List<ExerciseView> exercises = ExtrasHelper.getExerciseViewList(getIntent());
        return ExerciseSelectorFragment.newInstance(exercises);
    }

}
