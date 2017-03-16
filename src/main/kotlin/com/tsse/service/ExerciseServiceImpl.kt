package com.tsse.service

import com.tsse.domain.ExerciseAlreadyExistsException
import com.tsse.domain.ExerciseNotFoundException
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
class ExerciseServiceImpl(private val exerciseRepository: ExerciseRepository) : ExerciseService {

    override fun createExercise(exercise: Exercise): Exercise {
        if (exerciseRepository.findByName(exercise.name) != null) {
            throw ExerciseAlreadyExistsException(exercise)
        }

        return exerciseRepository.save(exercise)
    }

    override fun getAllExercises(): List<Exercise> = exerciseRepository.findAll()

    override fun getExerciseById(id: Long): Exercise = exerciseRepository.findOne(id) ?: throw ExerciseNotFoundException(id)

    override fun updateExercise(exercise: Exercise): Exercise {
        getExerciseById(exercise.id)
        return exerciseRepository.save(exercise)
    }

    override fun deleteAllExercises() = exerciseRepository.deleteAll()

    override fun deleteExercise(id: Long) = exerciseRepository.delete(id)
}
