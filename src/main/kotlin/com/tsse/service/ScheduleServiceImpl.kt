package com.tsse.service

import com.tsse.model.Schedule
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

    fun saveSchedule(schedule: Schedule): Boolean {
        repository.save(schedule)

        return true
    }

    fun getSchedule(id: Long): Schedule {
        val schedule = repository.findOne(id) ?: throw NotFoundException("Schedule not found!")
        return schedule
    }

    fun getAllSchedules(): List<Schedule> {

        return repository.findAll().toList()

    }

}