package br.com.sailboat.zerotohero.data.repository

import br.com.sailboat.zerotohero.domain.model.Exercise
import br.com.sailboat.zerotohero.domain.repository.ExerciseRepository

class ExerciseRepositoryImpl : ExerciseRepository {

    override suspend fun getExercise(id: Long): Exercise {
        return Exercise(
            id = 42L,
            name = "Kettlebell Swings"
        )
    }
}
