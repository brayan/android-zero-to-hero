package br.com.sailboat.zerotohero.view.exercise.insert;

import android.content.Intent;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.exception.EntityNotFoundException;
import br.com.sailboat.canoe.exception.RequiredFieldNotFilledException;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.canoe.helper.StringHelper;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.Extras;
import br.com.sailboat.zerotohero.model.sqlite.Exercise;
import br.com.sailboat.zerotohero.model.sqlite.ExerciseHistory;
import br.com.sailboat.zerotohero.persistence.DatabaseOpenHelper;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseHistorySQLite;
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
        executeAsyncWithProgress(new AsyncHelper.Callback() {
            @Override
            public void doInBackground() throws Exception {
                Exercise exercise = createExercise();

                if (hasExerciseToEdit()) {
                    ExerciseSQLite.newInstance(getContext()).update(exercise);
                } else {
                    long id = ExerciseSQLite.newInstance(getContext()).save(exercise);
                    exercise.setId(id);
                }

                ExerciseHistory newHistory = createHistoryExercise(exercise.getId());
                ExerciseHistory lastHistory = getLastHistory(exercise.getId());

                if (lastHistory == null || !lastHistory.equals(newHistory)) {
                    ExerciseHistorySQLite.newInstance(getContext()).save(newHistory);
                }

            }

            @Override
            public void onSuccess() {
                closeActivityWithResultOk();
            }

            @Override
            public void onFail(Exception e) {
                printLogAndShowDialog(e);
            }

            private Exercise createExercise() throws Exception {
                Exercise exercise = new Exercise();
                exercise.setId(getViewModel().getExerciseId());
                exercise.setName(getViewModel().getName());

                return exercise;
            }

            private ExerciseHistory createHistoryExercise(long exerciseId) throws Exception {
                ExerciseHistory history = new ExerciseHistory();
                history.setExerciseId(exerciseId);
                history.setWeight(getViewModel().getWeight());
                history.setSets(getViewModel().getSet());
                history.setReps(getViewModel().getRepetition());

                return history;
            }

            private boolean hasExerciseChange(ExerciseHistory history1, ExerciseHistory history2) {
                return (history1.getReps() == history2.getReps() && history1.getSets() == history2.getSets() && history1.getWeight() == history2.getWeight());
            }

        });

    }

    private void extractInfoFromViews() {
        getViewModel().setName(getView().getName());
        getViewModel().setWeight(getDoubleFromString(getView().getWeight()));
        getViewModel().setSet(getIntFromString(getView().getSets()));
        getViewModel().setRepetition(getIntFromString(getView().getReps()));
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
        Exercise exercise = new ExerciseSQLite(DatabaseOpenHelper.getInstance(getContext())).getExerciseById(exerciseId);
        getViewModel().setName(exercise.getName());

        ExerciseHistory history = getLastHistory(exerciseId);

        if (history != null) {
            getViewModel().setWeight(history.getWeight());
            getViewModel().setSet(history.getSets());
            getViewModel().setRepetition(history.getReps());
        }
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

    private ExerciseHistory getLastHistory(long exerciseId) {
        try {
            return ExerciseHistorySQLite.newInstance(getContext()).getMostRecentExerciseHistory(exerciseId);
        } catch (EntityNotFoundException ignore) {
            return null;
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
