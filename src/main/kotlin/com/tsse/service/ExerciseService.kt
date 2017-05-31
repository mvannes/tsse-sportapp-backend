package com.tsse.service

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
class ExerciseService(private val repository: ExerciseRepository) : AbstractService<Exercise>() {

    override fun repository() = repository
}
