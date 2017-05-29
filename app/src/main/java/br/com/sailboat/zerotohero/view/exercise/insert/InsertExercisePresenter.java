package br.com.sailboat.zerotohero.view.exercise.insert;

import android.os.Bundle;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.exception.EntityNotFoundException;
import br.com.sailboat.canoe.exception.RequiredFieldNotFilledException;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.canoe.helper.StringHelper;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.helper.ExtrasHelper;
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
    public void extractExtrasFromArguments(Bundle arguments) {
        long exerciseId = ExtrasHelper.getExerciseId(arguments);
        getViewModel().setExerciseId(exerciseId);
    }

    @Override
    protected void onResumeFirstSession() {
        if (hasExerciseToEdit()) {
            startEditingExercise();
        } else {
            showKeyboard();
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
                exercise.setNotes(getViewModel().getNotes());

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


        });

    }

    private void extractInfoFromViews() {
        getViewModel().setName(getView().getName());
        getViewModel().setWeight(getDoubleFromString(getView().getWeight()));
        getViewModel().setSet(getIntFromString(getView().getSets()));
        getViewModel().setRepetition(getIntFromString(getView().getReps()));
        getViewModel().setNotes(getView().getNotes());
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
        getViewModel().setNotes(exercise.getNotes());

        ExerciseHistory history = getLastHistory(exerciseId);

        if (history != null) {
            getViewModel().setWeight(history.getWeight());
            getViewModel().setSet(history.getSets());
            getViewModel().setRepetition(history.getReps());
        }
    }

    private void bindExerciseToView() {
        getView().setName(getViewModel().getName());
        getView().setWeight(formatValue(getViewModel().getWeight(), 1));
        getView().setSets(String.valueOf(getViewModel().getSet()));
        getView().setReps(String.valueOf(getViewModel().getRepetition()));
        getView().setNotes(String.valueOf(getViewModel().getNotes()));
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

    private void showKeyboard() {
        getView().showKeyboard();
    }


    public interface View extends BasePresenter.View {
        String getName();
        void setName(String name);
        void updateToolbarTitle(String title);
        String getWeight();
        String getSets();
        String getReps();
        String getNotes();
        void setWeight(String weight);
        void setSets(String set);
        void setReps(String repetition);
        void setNotes(String notes);
        void showKeyboard();
    }

}
