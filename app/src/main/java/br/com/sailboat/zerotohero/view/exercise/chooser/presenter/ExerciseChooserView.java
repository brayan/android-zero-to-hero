package br.com.sailboat.zerotohero.view.exercise.chooser.presenter;

import android.content.Context;

import java.util.List;

import br.com.sailboat.zerotohero.model.Exercise;

public interface ExerciseChooserView {

    Context getActivityContext();

    void updateExerciseListView();

    void showToast(String message);

    void updateTitle(String title);

    void updateVisibilityOfViews();

    void updateExerciseView(int position);

    void closeActivityResultCanceled();

    void closeActivityResultOk(List<Exercise> exercises);

    void updateMenu();

}
