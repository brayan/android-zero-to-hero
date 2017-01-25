package br.com.sailboat.zerotohero.view.ui.exercise.insert;

import android.content.Intent;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.exception.EntityNotFoundException;
import br.com.sailboat.canoe.exception.RequiredFieldNotFilledException;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.canoe.helper.StringHelper;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.model.Exercise;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;

public class InsertExercisePresenter extends BasePresenter<InsertExercisePresenter.View> {

    private InsertExerciseViewModel viewModel = new InsertExerciseViewModel();

    public InsertExercisePresenter(InsertExercisePresenter.View view) {
        super(view);
    }

    @Override
    public void extractExtrasFromIntent(Intent intent) {
        long exerciseId = Extras.getExerciseId(intent);
        getViewModel().setExerciseId(exerciseId);
    }

    @Override
    protected void onResumeFirstSession() {
        if (hasExerciseToEdit()) {
            startEditingExercise();
        }
    }

    @Override
    protected void postResume() {
        updateContentViews();
    }

    public void onClickMenuSave() {
        try {
            closeKeyboard();
            extractInfoFromViews();
            performValidations();
            saveExercise();

        } catch (RequiredFieldNotFilledException e) {
            showMessage(e.getMessage());

        } catch (Exception e) {
            printLogAndShowDialog(e);
        }
    }

    private void saveExercise() {

        AsyncHelper.execute(new AsyncHelper.Callback() {

            @Override
            public void doInBackground() throws Exception {
                prepareAndExercise();
            }

            @Override
            public void onSuccess() {
                closeActivityWithResultOk();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
            }

            private void prepareAndExercise() throws Exception {
                Exercise exercise = new Exercise();
                exercise.setId(getViewModel().getExerciseId());
                exercise.setName(getViewModel().getName());
                exercise.setWeight(getViewModel().getWeight());
                exercise.setSet(getViewModel().getSet());
                exercise.setRepetition(getViewModel().getRepetition());

                if (hasExerciseToEdit()) {
                    new ExerciseSQLite(getContext()).update(exercise);
                } else {
                    new ExerciseSQLite(getContext()).save(exercise);
                }

            }

        });
    }

    private void extractInfoFromViews() {
        getViewModel().setName(getView().getName());
        getViewModel().setWeight(getDoubleFromView(getView().getWeight()));
        getViewModel().setSet(getIntFromView(getView().getSets()));
        getViewModel().setRepetition(getIntFromView(getView().getReps()));
    }

    private void startEditingExercise() {
        AsyncHelper.execute(new AsyncHelper.Callback() {

            @Override
            public void doInBackground() throws Exception {
                long exerciseId = getViewModel().getExerciseId();
                loadExerciseInfo(exerciseId);
            }

            @Override
            public void onSuccess() {
                getView().setActivityToHideKeyboard();
                bindExerciseToView();
                updateContentViews();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
                closeActivityWithResultCanceled();
            }

        });
    }

    private void loadExerciseInfo(long exerciseId) throws EntityNotFoundException {
        Exercise exercise = new ExerciseSQLite(getContext()).getExerciseById(exerciseId);
        getViewModel().setName(exercise.getName());
        getViewModel().setWeight(exercise.getWeight());
        getViewModel().setSet(exercise.getSet());
        getViewModel().setRepetition(exercise.getRepetition());
    }

    private void bindExerciseToView() {
        getView().setName(getViewModel().getName());
        getView().setWeight(String.valueOf(getViewModel().getWeight()));
        getView().setSet(String.valueOf(getViewModel().getSet()));
        getView().setRepetition(String.valueOf(getViewModel().getRepetition()));
        closeKeyboard();
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
        return getViewModel().getExerciseId() != -1;
    }

    private InsertExerciseViewModel getViewModel() {
        return viewModel;
    }

    private void performValidations() throws Exception {

        if (StringHelper.isNullOrEmpty(getViewModel().getName())) {
            throw new RequiredFieldNotFilledException(getString(R.string.exeption_exercise_name));
        }

        if (getViewModel().getSet() == 0) {
            throw new RequiredFieldNotFilledException(getString(R.string.exeption_exercise_sets));
        }

        if (getViewModel().getRepetition() == 0) {
            throw new RequiredFieldNotFilledException(getString(R.string.exeption_exercise_reps));
        }

    }


    public interface View extends BasePresenter.View {
        String getName();
        void setName(String name);
        void updateToolbarTitle(String title);
        String getWeight();
        String getSets();
        String getReps();
        void setWeight(String weight);
        void setSet(String set);
        void setRepetition(String repetition);
    }

}
