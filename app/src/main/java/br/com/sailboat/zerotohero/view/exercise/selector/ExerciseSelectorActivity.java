package br.com.sailboat.zerotohero.view.exercise.selector;

import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.List;

import br.com.sailboat.canoe.base.BaseActivitySingleFragment;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.model.view.ExerciseView;

public class ExerciseSelectorActivity extends BaseActivitySingleFragment<ExerciseSelectorFragment> {

    public static void start(Fragment fragment, List<ExerciseView> exercises, int requestCode) {
        Intent starter = new Intent(fragment.getActivity(), ExerciseSelectorActivity.class);
        Extras.putExerciseViewList(exercises, starter);
        fragment.startActivityForResult(starter, requestCode);
    }

    @Override
    protected ExerciseSelectorFragment newFragmentInstance() {
        return ExerciseSelectorFragment.newInstance(Extras.getExerciseViewList(getIntent()));
    }
}
