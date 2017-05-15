package com.tsse.service

import com.tsse.domain.WorkoutNotFoundException
import com.tsse.domain.model.Workout
import com.tsse.repository.WorkoutRepository
import org.springframework.stereotype.Service

/**
 * @author Floris van Lent
 * @version 1.0.1
 */
@Service
class WorkoutServiceImpl(val repository: WorkoutRepository) : WorkoutService {

    override fun saveWorkout(workout: Workout) = repository.save(workout)

    override fun getWorkout(id: Long): Workout = repository.findOne(id) ?: throw WorkoutNotFoundException(id)

    override fun getAllWorkouts() = repository.findAll().toList()

    override fun updateWorkout(workout: Workout): Workout {
        getWorkout(workout.id)
        return repository.save(workout)
    }

    override fun deleteWorkout(id: Long) = repository.delete(id)
}
