package com.tsse.service.impl

import com.tsse.domain.model.Schedule
import com.tsse.repository.ScheduleRepository
import com.tsse.service.AbstractResourceService
import org.springframework.stereotype.Service

/**
 * Service used for schedules.
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
@Service
class ScheduleService(val repository: ScheduleRepository) : AbstractResourceService<Schedule>() {

    override fun repository() = repository

    override fun findByNameApi() = null
}