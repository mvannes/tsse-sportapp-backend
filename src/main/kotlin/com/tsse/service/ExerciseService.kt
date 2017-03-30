package com.tsse.service

import com.tsse.domain.Category
import com.tsse.domain.Exercise
import org.springframework.validation.Errors

/**
 * Service interface for exercises.
 *
 * @author Boyd Hogerheijde
 * @version 1.0.0
 */
interface ExerciseService {

    fun createExercise(exercise: Exercise): Exercise

    fun getAllExercises(): List<Exercise>

    fun getExerciseById(id: Long): Exercise

    fun getExerciseByName(name: String): Exercise

    fun getExercisesByCategory(category: Category): List<Exercise>

    fun updateExercise(exercise: Exercise): Exercise

    fun deleteAllExercises()

    fun deleteExercise(id: Long)
}