package br.com.sailboat.zerotohero.android.feature.exercise.list.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import br.com.sailboat.zerotohero.domain.model.Exercise

internal class ExerciseListViewState {
    val exerciseList = MutableLiveData<List<Exercise>>()
}
