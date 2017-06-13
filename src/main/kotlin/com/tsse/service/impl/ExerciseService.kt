package com.tsse.service.impl

import com.tsse.domain.model.Exercise
import com.tsse.repository.ExerciseRepository
import com.tsse.service.AbstractResourceService
import org.springframework.stereotype.Service

/**
 * Service implementation for exercises.
 *
 * @author Boyd Hogerheijde
 * @version 1.0.0
 */
@Service
class ExerciseService(private val repository: ExerciseRepository) : AbstractResourceService<Exercise>() {

    override fun repository() = repository

    override fun findByNameApi() = repository
}
