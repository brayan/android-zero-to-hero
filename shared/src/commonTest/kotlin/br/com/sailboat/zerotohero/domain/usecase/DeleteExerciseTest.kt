package br.com.sailboat.zerotohero.domain.usecase

import br.com.sailboat.zerotohero.domain.repository.ExerciseRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class DeleteExerciseTest {

    private val exerciseRepository = mockk<ExerciseRepository>()

    private val deleteExercise = DeleteExercise(exerciseRepository)

    @Test
    fun `should delete exercise from repository`() = runBlocking {
        prepareScenario()

        deleteExercise(id = 42L)

        coVerify { exerciseRepository.deleteExercise(42L) }
    }

    private fun prepareScenario() {
        coEvery { exerciseRepository.deleteExercise(any()) } just runs
    }
}
