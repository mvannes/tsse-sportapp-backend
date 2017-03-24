package com.tsse.repository

import com.tsse.model.Workout
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

/**
 * Repository for workouts.
 *
 * @author Fabian de Almeida Ramos
 *
 * @version 1.0.0
 */
@RepositoryRestResource
interface WorkoutRepository : CrudRepository<Workout, Long>