package com.tsse.repository

import com.tsse.model.Schedule
import org.springframework.data.repository.CrudRepository

/**
 * Repository for schedules.
 *
 * @author Fabian de Almeida Ramos
 *
 * @version 1.0.0
 */
interface ScheduleRepository : CrudRepository<Schedule, Long>