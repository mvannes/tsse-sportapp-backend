package com.tsse.repository

import com.tsse.domain.model.Workout
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Repository for workouts.
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
@Repository
interface WorkoutRepository : JpaRepository<Workout, Long> {
    fun findById(id: Long): Workout?
}