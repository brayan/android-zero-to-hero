package br.com.sailboat.zerotohero.domain.repository

import br.com.sailboat.zerotohero.domain.model.Exercise

interface ExerciseRepository {
    suspend fun getExercise(id: Long): Exercise
    suspend fun deleteExercise(id: Long)
    suspend fun save(exercise: Exercise)
}
