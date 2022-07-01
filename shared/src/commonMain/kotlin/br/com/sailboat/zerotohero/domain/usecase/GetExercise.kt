package br.com.sailboat.zerotohero.domain.usecase

import br.com.sailboat.zerotohero.domain.model.Exercise
import br.com.sailboat.zerotohero.domain.repository.ExerciseRepository

class GetExercise(
    private val exerciseRepository: ExerciseRepository,
) : GetExerciseUseCase {

    override suspend fun invoke(id: Long): Exercise {
        return exerciseRepository.getExercise(id)
    }
}
