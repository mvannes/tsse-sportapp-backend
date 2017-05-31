package com.tsse.service

import com.tsse.domain.ExerciseAlreadyExistsException
import com.tsse.domain.ExerciseNotFoundException
import com.tsse.domain.model.Category
import com.tsse.domain.model.Exercise
import com.tsse.repository.ExerciseRepository
import org.springframework.stereotype.Service

/**
 * Service implementation for exercises.
 *
 * @author Boyd Hogerheijde
 * @version 1.0.0
 */
@Service
class ExerciseServiceImpl(private val repository: ExerciseRepository) : ExerciseService {

    override fun createExercise(exercise: Exercise): Exercise {
        if (repository.findByName(exercise.name) != null) {
            throw ExerciseAlreadyExistsException(exercise)
        }

        return repository.save(exercise)
    }

    override fun getAllExercises(): List<Exercise> = repository.findAll()

    override fun getExercisesByCategory(category: Category): List<Exercise> = repository.findByCategory(category)

    override fun getExerciseById(id: Long): Exercise = repository.findOne(id) ?: throw ExerciseNotFoundException(id)

    override fun getExerciseByName(name: String): Exercise = repository.findByName(name) ?: throw ExerciseNotFoundException(name)

    override fun updateExercise(exercise: Exercise): Exercise {
        getExerciseById(exercise.id)

        return repository.save(exercise)
    }

    override fun deleteAllExercises() = repository.deleteAll()

    override fun deleteExercise(id: Long) = repository.delete(id)

}
