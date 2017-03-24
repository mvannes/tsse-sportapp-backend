package com.tsse.service

import com.tsse.domain.*
import com.tsse.repository.ExerciseRepository
import org.springframework.stereotype.Service
import org.springframework.validation.Errors

/**
 * Service implementation for exercises.
 *
 * @author Boyd Hogerheijde
 * @version 1.0.0
 */
@Service
class ExerciseServiceImpl(private val exerciseRepository: ExerciseRepository) : ExerciseService {

    override fun createExercise(exercise: Exercise): Exercise {
        if (exerciseRepository.findByName(exercise.name) != null) {
            throw ExerciseAlreadyExistsException(exercise)
        }

        return exerciseRepository.save(exercise)
    }

    override fun getAllExercises(): List<Exercise> = exerciseRepository.findAll()

    override fun getExercisesByCategory(category: Category): List<Exercise> = exerciseRepository.findByCategory(category)

    override fun getExerciseById(id: Long): Exercise = exerciseRepository.findOne(id) ?: throw ExerciseNotFoundException(id)

    override fun getExerciseByName(name: String): Exercise = exerciseRepository.findByName(name) ?: throw ExerciseNotFoundException(name)

    override fun updateExercise(exercise: Exercise): Exercise {
        getExerciseById(exercise.id)

        return exerciseRepository.save(exercise)
    }

    override fun deleteAllExercises() = exerciseRepository.deleteAll()

    override fun deleteExercise(id: Long) = exerciseRepository.delete(id)

}
