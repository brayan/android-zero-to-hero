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

    override suspend fun getExercises(): List<Exercise> {
        return listOf(
            Exercise(
                id = 42L,
                name = "Kettlebell Swings"
            )
        )
    }

    override suspend fun deleteExercise(id: Long) {
        // TODO: delete exercise
    }

    override suspend fun save(exercise: Exercise) {
        // TODO: save exercise
    }
}
