package com.tsse.repository

import com.tsse.domain.model.Exercise
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Repository for exercises.
 *
 * @author Boyd Hogerheijde
 * @version 1.0.1
 */
@Repository
interface ExerciseRepository : JpaRepository<Exercise, Long>, ByNameApi<Exercise>
