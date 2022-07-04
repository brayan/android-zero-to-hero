package br.com.sailboat.zerotohero.domain.usecase

interface DeleteExerciseUseCase {
    suspend operator fun invoke(id: Long)
}
