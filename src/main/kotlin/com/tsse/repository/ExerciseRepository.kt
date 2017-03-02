package com.tsse.repository

import com.tsse.model.Exercise
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

/**
 * Repository for exercises.
 *
 * @author Fabian de Almeida Ramos
 * @author Boyd Hogerheijde
 *
 * @version 1.0.0
 */
@RepositoryRestResource
interface ExerciseRepository : CrudRepository<Exercise, Long>
