package br.com.sailboat.zerotohero.domain.usecase

import br.com.sailboat.zerotohero.domain.model.Exercise
import br.com.sailboat.zerotohero.domain.repository.ExerciseRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class SaveExerciseTest {

    private val exerciseRepository = mockk<ExerciseRepository>()

    private val saveExercise = SaveExercise(exerciseRepository)

    @Test
    fun `should save exercise on repository`() = runBlocking {
        val exercise = Exercise(id = 42L, name = "Kettlebell Swing")
        prepareScenario()

        saveExercise(exercise)

        coVerify { exerciseRepository.save(exercise) }
    }

    private fun prepareScenario() {
        coEvery { exerciseRepository.save(any()) } just runs
    }
}
