package br.com.sailboat.zerotohero.view.exercise.details.presenter;

import android.content.Context;

import br.com.sailboat.zerotohero.model.Exercise;

public interface ExerciseDetailsView {

    Context getActivityContext();

    void showToast(String message);

    void startEditExerciseActivity(Exercise exercise);

    void closeActivityWithResultCanceled();

    void closeActivityWithResultOkAndDeleteExercise(Exercise exercise);

    void setRepetition(String name);

    void setWeight(String weight);

    void setSet(String set);

    void setName(String name);
}
