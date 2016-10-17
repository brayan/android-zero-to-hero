package br.com.sailboat.zerotohero.view.exercise.insert_or_edit.presenter;

import android.support.annotation.StringRes;

import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.base.BasePresenter;
import br.com.sailboat.zerotohero.helper.StringHelper;
import br.com.sailboat.zerotohero.model.Exercise;

public class InsertOrEditExercisePresenter extends BasePresenter {

    private InsertOrEditExerciseView view;
    private InsertOrEditExerciseViewModel viewModel;

    public InsertOrEditExercisePresenter(InsertOrEditExerciseView view) {
        setView(view);
        setViewModel(new InsertOrEditExerciseViewModel());
    }

    @Override
    protected void onResumeFirstSession() {
        if (hasExerciseToEdit()) {
            getView().setName(getViewModel().getExercise().getName());
            getView().setWeight(String.valueOf(getViewModel().getExercise().getWeight()));
            getView().setSet(String.valueOf(getViewModel().getExercise().getSet()));
            getView().setRepetition(String.valueOf(getViewModel().getExercise().getRepetition()));
            getView().hideKeyboard();
        }

//        else {
//            getView().openKeyboard();
//        }
    }

    @Override
    protected void postResume() {
        updateContentViews();
    }

    public void onClickMenuSave() {
        try {
            checkRequiredComponents();
            buildAndReturnEntities();
        } catch (Exception e) {
            getView().showDialog(e.getMessage());
        }
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

    public InsertOrEditExerciseViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(InsertOrEditExerciseViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public InsertOrEditExerciseView getView() {
        return view;
    }

    public void setView(InsertOrEditExerciseView view) {
        this.view = view;
    }

    private void buildAndReturnEntities() {
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

        if (StringHelper.isEmpty(name)) {
            throw new Exception(getString(R.string.exeption_exercise_name));
        }

        String weight = getView().getWeight();

        if (StringHelper.isEmpty(weight)) {
            throw new Exception(getString(R.string.exeption_exercise_weight));
        }

        String sets = getView().getSets();

        if (StringHelper.isEmpty(sets)) {
            throw new Exception(getString(R.string.exeption_exercise_sets));
        }

        String reps = getView().getReps();

        if (StringHelper.isEmpty(reps)) {
            throw new Exception(getString(R.string.exeption_exercise_reps));
        }
    }

    private String getString(@StringRes int id) {
        return getView().getActivityContext().getString(id);
    }

    public void onReceiveExercise(Exercise exercise) {
        getViewModel().setExercise(exercise);
    }
}
