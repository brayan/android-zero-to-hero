package com.brayanbedritchuk.zerotohero.view.exercise.details.presenter;

import android.content.Context;

import com.brayanbedritchuk.zerotohero.model.Exercise;

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
