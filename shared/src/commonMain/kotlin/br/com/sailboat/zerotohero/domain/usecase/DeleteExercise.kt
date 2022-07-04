package br.com.sailboat.zerotohero.domain.usecase

import br.com.sailboat.zerotohero.domain.repository.ExerciseRepository

class DeleteExercise(
    private val exerciseRepository: ExerciseRepository,
) : DeleteExerciseUseCase {

    override suspend fun invoke(id: Long) {
        exerciseRepository.deleteExercise(id)
    }
}
