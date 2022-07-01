package br.com.sailboat.zerotohero.domain.usecase

import br.com.sailboat.zerotohero.domain.model.Exercise
import br.com.sailboat.zerotohero.domain.repository.ExerciseRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class GetExerciseTest {

    private val exerciseRepository = mockk<ExerciseRepository>()

    private val getExercise = GetExercise(exerciseRepository)

    @Test
    fun `should get exercise from repository`() = runBlocking {
        val exercise = Exercise(id = 42L, name = "Kettlebell Swings")
        prepareScenario(exercise = exercise)

        val result = getExercise(id = 42L)

        assertEquals(exercise, result)
    }

    private fun prepareScenario(
        exercise: Exercise = Exercise(id = 28L, name = "Exercise"),
    ) {
        coEvery { exerciseRepository.getExercise(any()) } returns exercise
    }
}
