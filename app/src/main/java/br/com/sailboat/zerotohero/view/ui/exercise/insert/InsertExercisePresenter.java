package br.com.sailboat.zerotohero.view.ui.exercise.insert;

import android.content.Context;
import android.content.Intent;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.helper.StringHelper;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.model.Exercise;

public class InsertExercisePresenter extends BasePresenter<InsertExercisePresenter.View> {

    private InsertExerciseViewModel viewModel = new InsertExerciseViewModel();

    public InsertExercisePresenter(InsertExercisePresenter.View view) {
        super(view);
    }

    @Override
    protected void onResumeFirstSession() {
        if (hasExerciseToEdit()) {
            bindExerciseToView();
        }
    }

    @Override
    protected void postResume() {
        updateContentViews();
    }

    @Override
    public void extractExtrasFromIntent(Intent intent) {
        Exercise exercise = Extras.getExercise(intent);
        getViewModel().setExercise(exercise);
    }

    public void onClickMenuSave() {
        try {
            checkRequiredComponents();
            buildAndReturnExercise();
        } catch (Exception e) {
            getView().showDialog(e.getMessage());
        }
    }

    private void bindExerciseToView() {
        getView().setName(getViewModel().getExercise().getName());
        getView().setWeight(String.valueOf(getViewModel().getExercise().getWeight()));
        getView().setSet(String.valueOf(getViewModel().getExercise().getSet()));
        getView().setRepetition(String.valueOf(getViewModel().getExercise().getRepetition()));
        getView().hideKeyboard();
    }

    private void updateContentViews() {
        updateToolbarTitle();
    }

    private void updateToolbarTitle() {
        String title = null;

        if (hasExerciseToEdit()) {
            title = getString(R.string.edit_exercise);
        } else {
            title = getString(R.string.new_exercise);
        }

        getView().updateToolbarTitle(title);
    }

    private boolean hasExerciseToEdit() {
        return getViewModel().getExercise() != null;
    }

    private InsertExerciseViewModel getViewModel() {
        return viewModel;
    }

    private void buildAndReturnExercise() {
        Exercise exercise = getViewModel().getExercise();

        if (exercise == null) {
            exercise = new Exercise();
            exercise.setId(-1);
        }

        exercise.setName(getView().getName());
        exercise.setWeight(Double.valueOf(getView().getWeight()));
        exercise.setSet(Integer.valueOf(getView().getSets()));
        exercise.setRepetition(Integer.valueOf(getView().getReps()));

        getView().closeActivityWithResultOk(exercise);
    }

    private void checkRequiredComponents() throws Exception {
        String name = getView().getName();

        if (StringHelper.isNullOrEmpty(name)) {
            throw new Exception(getString(R.string.exeption_exercise_name));
        }

        String weight = getView().getWeight();

        if (StringHelper.isNullOrEmpty(weight)) {
            throw new Exception(getString(R.string.exeption_exercise_weight));
        }

        String sets = getView().getSets();

        if (StringHelper.isNullOrEmpty(sets)) {
            throw new Exception(getString(R.string.exeption_exercise_sets));
        }

        String reps = getView().getReps();

        if (StringHelper.isNullOrEmpty(reps)) {
            throw new Exception(getString(R.string.exeption_exercise_reps));
        }
    }



    public interface View extends BasePresenter.View {
        Context getActivityContext();
        void showToast(String message);
        String getName();
        void showDialog(String message);
        void setName(String name);
        void hideKeyboard();
        void updateToolbarTitle(String title);
        void closeActivityWithResultOk(Exercise exercise);
        String getWeight();
        String getSets();
        String getReps();
        void setWeight(String weight);
        void setSet(String set);
        void setRepetition(String repetition);
    }

}
