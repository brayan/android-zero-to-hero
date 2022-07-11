package br.com.sailboat.zerotohero.presentation

import androidx.lifecycle.viewModelScope
import br.com.sailboat.zerotohero.base.BaseViewModel
import br.com.sailboat.zerotohero.domain.usecase.GetExercises
import kotlinx.coroutines.launch

internal class ExerciseListViewModel(
    override val viewState: ExerciseListViewState = ExerciseListViewState(),
    private val getExercises: GetExercises,
) : BaseViewModel<ExerciseListViewState, ExerciseListViewAction>() {

    override fun dispatchViewAction(viewAction: ExerciseListViewAction) {
        when (viewAction) {
            ExerciseListViewAction.OnStart -> onStart()
        }
    }

    private fun onStart() = viewModelScope.launch {
        val exercises = getExercises()
        viewState.exerciseList.value = exercises
    }
}
