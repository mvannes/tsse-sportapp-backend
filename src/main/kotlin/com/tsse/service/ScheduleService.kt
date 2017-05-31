package com.tsse.service

import com.tsse.domain.model.Schedule
import com.tsse.repository.ScheduleRepository
import org.springframework.stereotype.Service

/**
 * Service used for schedules.
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
@Service
class ScheduleService(val repository: ScheduleRepository) : AbstractService<Schedule>() {

    override fun repository() = repository
}