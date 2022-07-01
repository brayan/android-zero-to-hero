package br.com.sailboat.zerotohero.domain.usecase

import br.com.sailboat.zerotohero.domain.model.Exercise

interface GetExerciseUseCase {
    suspend operator fun invoke(id: Long): Exercise
}
