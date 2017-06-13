package com.tsse.service.impl

import com.tsse.domain.model.Workout
import com.tsse.repository.ByNameApi
import com.tsse.repository.WorkoutRepository
import com.tsse.service.AbstractResourceService
import org.springframework.stereotype.Service

/**
 * @author Floris van Lent
 * @version 1.0.1
 */
@Service
class WorkoutService(val repository: WorkoutRepository) : AbstractResourceService<Workout>() {

    override fun repository() = repository

    override fun findByNameApi() = null
}
