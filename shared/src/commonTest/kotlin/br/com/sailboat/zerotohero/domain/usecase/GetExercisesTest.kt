package br.com.sailboat.zerotohero.domain.usecase

import br.com.sailboat.zerotohero.domain.model.Exercise
import br.com.sailboat.zerotohero.domain.repository.ExerciseRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class GetExercisesTest {

    private val exerciseRepository = mockk<ExerciseRepository>()

    private val getExercises = GetExercises(exerciseRepository)

    @Test
    fun `should get exercises from repository`() = runBlocking {
        val exercises = listOf(Exercise(id = 42L, name = "Kettlebell Swings"))
        prepareScenario(exercises = exercises)

        val result = getExercises()

        assertEquals(exercises, result)
    }

    private fun prepareScenario(
        exercises: List<Exercise> = listOf(Exercise(id = 28L, name = "Exercise")),
    ) {
        coEvery { exerciseRepository.getExercises() } returns exercises
    }
}
