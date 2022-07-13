package br.com.sailboat.zerotohero.android.feature.exercise.list.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.sailboat.zerotohero.android.utility.android.CoroutinesTestRule
import br.com.sailboat.zerotohero.domain.model.Exercise
import br.com.sailboat.zerotohero.domain.usecase.GetExercisesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
internal class ExerciseListViewModelTest {

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val getExercisesUseCase: GetExercisesUseCase = mockk(relaxed = true)

    private val viewModel = ExerciseListViewModel(
        getExercisesUseCase = getExercisesUseCase,
    )

    @Test
    fun `should call getExercisesUseCase when dispatchViewAction is called with OnStart`() {
        runBlocking {
            val exercises = listOf(
                Exercise(
                    id = 42L,
                    name = "Kettlebell Swing",
                )
            )
            prepareScenario(exercises = exercises)

            viewModel.dispatchViewAction(ExerciseListViewAction.OnStart)

            coVerify { getExercisesUseCase() }
            assertEquals(exercises, viewModel.viewState.exerciseList.value)
        }
    }

    private fun prepareScenario(
        exercises: List<Exercise> = listOf(
            Exercise(
                id = 21L,
                name = "Exercise Name",
            )
        ),
    ) {
        coEvery { getExercisesUseCase() } returns exercises
    }
}
