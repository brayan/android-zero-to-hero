package br.com.sailboat.zerotohero.android.feature.exercise.list.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.sailboat.zerotohero.android.base.BaseViewModel
import br.com.sailboat.zerotohero.domain.usecase.GetExercisesUseCase
import kotlinx.coroutines.launch

internal class ExerciseListViewModel(
    override val viewState: ExerciseListViewState = ExerciseListViewState(),
    private val getExercisesUseCase: GetExercisesUseCase,
) : BaseViewModel<ExerciseListViewState, ExerciseListViewAction>() {

    override fun dispatchViewAction(viewAction: ExerciseListViewAction) {
        when (viewAction) {
            ExerciseListViewAction.OnStart -> onStart()
        }
    }

    private fun onStart() = viewModelScope.launch {
        val exercises = getExercisesUseCase()
        viewState.exerciseList.value = exercises
    }
}
