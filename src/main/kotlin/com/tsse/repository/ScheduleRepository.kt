package com.tsse.repository

import com.tsse.domain.Schedule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Repository for schedules.
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
@Repository
interface ScheduleRepository : JpaRepository<Schedule, Long>