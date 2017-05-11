package com.tsse.service

import com.tsse.domain.ExerciseNotFoundException
import com.tsse.domain.Workout
import com.tsse.repository.WorkoutRepository
import org.springframework.stereotype.Service

/**
 * @author Floris van Lent
 * @version 1.0.0
 */
@Service
class WorkoutServiceImpl(val repository: WorkoutRepository) : WorkoutService {

    override fun saveWorkout(workout: Workout) = repository.save(workout)

    // Temp Exception
    override fun getWorkout(id: Long): Workout = repository.findOne(id) ?: throw ExerciseNotFoundException(id)

    override fun getAllWorkouts() = repository.findAll().toList()

    override fun updateWorkout(workout: Workout): Workout {
        getWorkout(workout.id)
        return repository.save(workout)
    }

    override fun deleteWorkout(id: Long) = repository.delete(id)
}
