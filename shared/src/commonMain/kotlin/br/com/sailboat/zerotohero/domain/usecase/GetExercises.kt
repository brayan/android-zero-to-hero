package br.com.sailboat.zerotohero.domain.usecase

import br.com.sailboat.zerotohero.domain.model.Exercise
import br.com.sailboat.zerotohero.domain.repository.ExerciseRepository

class GetExercises(
    private val exerciseRepository: ExerciseRepository,
) : GetExercisesUseCase {

    override suspend fun invoke(): List<Exercise> {
        return exerciseRepository.getExercises()
    }
}
