package br.com.sailboat.zerotohero.domain.usecase

import br.com.sailboat.zerotohero.domain.model.Exercise

interface GetExercisesUseCase {
    suspend operator fun invoke(): List<Exercise>
}
