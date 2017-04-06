package com.tsse.service

import com.tsse.domain.ExerciseNotFoundException
import com.tsse.domain.Workout
import com.tsse.repository.WorkoutRepository
import org.springframework.stereotype.Service

/**
 * @author Floris on 06-04-2017.
 * @version 1.0.0
 */
@Service
class WorkoutServiceImpl(val repository: WorkoutRepository) : WorkoutService {

    override fun saveWorkout(schedule: Workout) = repository.save(schedule)

    // Temp Exception
    override fun getWorkout(id: Long): Workout = repository.findOne(id) ?: throw ExerciseNotFoundException(id)

    override fun getAllWorkouts() = repository.findAll().toList()

    override fun updateWorkout(schedule: Workout): Workout {
        getWorkout(schedule.id)
        return repository.save(schedule)
    }

    override fun deleteWorkout(id: Long) = repository.delete(id)
}
