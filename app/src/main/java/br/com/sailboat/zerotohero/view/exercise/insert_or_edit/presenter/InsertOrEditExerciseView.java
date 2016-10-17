package br.com.sailboat.zerotohero.view.exercise.insert_or_edit.presenter;

import android.content.Context;

import br.com.sailboat.zerotohero.model.Exercise;

public interface InsertOrEditExerciseView {

    Context getActivityContext();

    void showToast(String message);

    String getName();

    void showDialog(String message);

    void closeActivityWithResultCanceled();

    void setName(String name);

    void hideKeyboard();

    void updateToolbarTitle(String title);

    void closeActivityWithResultOk(Exercise exercise);

    void openKeyboard();

    String getWeight();

    String getSets();

    String getReps();

    void setWeight(String weight);

    void setSet(String set);

    void setRepetition(String repetition);
}
