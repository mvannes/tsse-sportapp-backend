package com.tsse.service

import com.tsse.domain.model.Workout

/**
 * @author Floris van Lent
 * @version 1.0.0
 */
interface WorkoutService {

    fun saveWorkout(workout: Workout): Workout

    fun getWorkout(id: Long): Workout

    fun getAllWorkouts(): List<Workout>

    fun updateWorkout(workout: Workout): Workout

    fun deleteWorkout(id: Long)

}