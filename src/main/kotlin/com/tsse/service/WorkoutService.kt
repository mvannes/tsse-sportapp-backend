package com.tsse.service

import com.tsse.domain.model.Workout
import com.tsse.repository.WorkoutRepository
import org.springframework.stereotype.Service

/**
 * @author Floris van Lent
 * @version 1.0.1
 */
@Service
class WorkoutService(val repository: WorkoutRepository) : AbstractService<Workout>() {

    override fun repository() = repository
}
