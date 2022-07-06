package br.com.sailboat.zerotohero.domain.usecase

import br.com.sailboat.zerotohero.domain.model.Exercise

interface SaveExerciseUseCase {
    suspend operator fun invoke(exercise: Exercise)
}
