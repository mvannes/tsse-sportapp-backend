package com.tsse.service

import com.tsse.domain.Schedule
import com.tsse.domain.ScheduleNotFoundException
import com.tsse.repository.ScheduleRepository
import javassist.NotFoundException
import org.springframework.stereotype.Service

/**
 * Service used for schedules.
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
@Service
class ScheduleServiceImpl(val repository: ScheduleRepository) {

    fun saveSchedule(schedule: Schedule): Schedule {
        return repository.save(schedule)
    }

    fun getSchedule(id: Long): Schedule = repository.findOne(id) ?: throw ScheduleNotFoundException(id)

    fun getAllSchedules(): List<Schedule> {

        return repository.findAll().toList()

    }

}