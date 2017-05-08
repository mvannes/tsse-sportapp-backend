package com.tsse.service

import com.tsse.domain.model.Schedule


/**
 * Service used for schedules.
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
interface ScheduleService {

    fun saveSchedule(schedule: Schedule): Schedule

    fun getSchedule(id: Long): Schedule

    fun getAllSchedules(): List<Schedule>

    fun updateSchedule(schedule: Schedule): Schedule

    fun deleteSchedule(id: Long)

}