package br.com.sailboat.zerotohero.domain.usecase

import br.com.sailboat.zerotohero.domain.model.Exercise
import br.com.sailboat.zerotohero.domain.repository.ExerciseRepository

class SaveExercise(
    private val exerciseRepository: ExerciseRepository,
) : SaveExerciseUseCase {

    override suspend fun invoke(exercise: Exercise) {
        exerciseRepository.save(exercise)
    }
}
